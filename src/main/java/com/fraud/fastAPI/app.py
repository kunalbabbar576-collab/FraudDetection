import os
from typing import Optional

from fastapi import FastAPI
from pydantic import BaseModel

import torch
from transformers import AutoTokenizer
from optimum.onnxruntime import ORTModelForSequenceClassification


# ---------- Config (override via env if you like) ----------
MODEL_DIR = os.getenv("MODEL_DIR",  "C:/Users/user/Downloads/fraud-backend/fraud-backend/FraudDetection/src/main/java/com/fraud/fastAPI/model")
MAX_LEN = int(os.getenv("MAX_LEN", "512"))
THRESHOLD = float(os.getenv("THRESHOLD", "0.65"))
# -----------------------------------------------------------

app = FastAPI(title="Scam Detector (ONNX + FastAPI)", version="1.0.0")

# Load once at startup
print("Loading ONNX model and tokenizer...")
tokenizer = AutoTokenizer.from_pretrained(MODEL_DIR, local_files_only=True)
model = ORTModelForSequenceClassification.from_pretrained(MODEL_DIR, local_files_only=True)
model.eval()
print("✓ ONNX model and tokenizer loaded.")


class PredictRequest(BaseModel):
    conversation: str
    threshold: Optional[float] = None  # allow per-request override


class PredictResponse(BaseModel):
    score: float     # probability that the conversation is scam (class=1)
    isScam: bool
    # probs could be useful for debugging or dashboards
    probs: Optional[list] = None  # [p(non-scam), p(scam)]


@app.get("/health")
def health():
    return {"status": "ok", "model_dir": MODEL_DIR, "max_len": MAX_LEN}


@app.post("/predict", response_model=PredictResponse)
def predict(req: PredictRequest):
    text = (req.conversation or "").strip()
    if not text:
        return PredictResponse(score=0.0, isScam=False, probs=[1.0, 0.0])

    # Tokenize → PyTorch tensors (ORTModel wrapper accepts these)
    enc = tokenizer(
        text,
        return_tensors="pt",
        truncation=True,
        max_length=MAX_LEN,
        padding="max_length"  # consistent length for ONNX graphs
    )

    with torch.inference_mode():
        outputs = model(**enc)               # ORT inference under the hood
        logits = outputs.logits              # shape: [1, 2]
        probs = torch.softmax(logits, dim=-1)[0]  # shape: [2]
        p_scam = probs[1].item()                  # class index 1 = scam

    thr = req.threshold if req.threshold is not None else THRESHOLD
    is_scam = p_scam > thr

    return PredictResponse(
        score=p_scam,
        isScam=is_scam,
        probs=[probs[0].item(), probs[1].item()]
    )
