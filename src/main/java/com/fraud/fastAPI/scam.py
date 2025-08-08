import torch
from transformers import AutoTokenizer, AutoModelForSequenceClassification
from optimum.onnxruntime import ORTModelForSequenceClassification

def load_models_and_tokenizers():
    """Load both PyTorch and ONNX models with their tokenizers."""
    
    # Paths
    original_model_path = "./electra-scam-v7-split-conversations-with-noise-no-user"
    onnx_model_path = "./onnx_models/electra-scam-v7-no-user-local"
    
    print("Loading models and tokenizers...")
    
    # Load ONNX model and tokenizer
    print("2. Loading ONNX model...")
    onnx_tokenizer = AutoTokenizer.from_pretrained(onnx_model_path)
    onnx_model = ORTModelForSequenceClassification.from_pretrained(onnx_model_path)
    
    print("✓ Models loaded successfully!")
    return onnx_model, onnx_tokenizer

def result(onnx_model,onnx_tokenizer, text):
     # ONNX tokenizer
        onnx_inputs = onnx_tokenizer(text, return_tensors="pt", truncation=True, max_length=512)
        onnx_outputs = onnx_model(**onnx_inputs)
        onnx_probs = torch.softmax(onnx_outputs.logits, dim=-1)
        onnx_pred = torch.argmax(onnx_outputs.logits, dim=-1)
        onnx_prob_scam = onnx_probs[0][1].item()

def main():
        model, tokenizer= load_models_and_tokenizers()
        results = result(model, tokenizer, "conversation")
