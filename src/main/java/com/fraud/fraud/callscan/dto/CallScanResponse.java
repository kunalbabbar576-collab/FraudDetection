package com.fraud.fraud.callscan.dto;
public class CallScanResponse {
    private double score;
    private boolean isScam;

    public CallScanResponse(double score, boolean isScam) {
        this.score = score;
        this.isScam = isScam;
    }

    public double getScore() {
        return score;
    }

    public boolean isScam() {
        return isScam;
    }
}