package com.fraud.fraud.textscan.dto;

public class ScanResponse {
    private double score;
    private boolean isScam;

    public ScanResponse(double score, boolean isScam) {
        this.score = score;
        this.isScam = isScam;
    }

    public boolean isScam() {
        return isScam;
    }
    public double getScore() {
        return score;
    }
}
