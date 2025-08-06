from fastapi import FastAPI, Request

app = FastAPI()

@app.post("/predict")
async def predict(request: Request):
    data = await request.json()
    message = data.get("message", "")
    
    if "otp" in message.lower() or "verify" in message.lower():
        return {"score": 0.87}
    else:
        return {"score": 0.42}
