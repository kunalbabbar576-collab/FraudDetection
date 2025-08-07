package com.fraud.frauddecision.dto;

public class FraudDecisionResponse {
    private double finalScore;
    private boolean isScam;

    public FraudDecisionResponse(double finalScore, boolean isScam) {
        this.finalScore = finalScore;
        this.isScam = isScam;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public boolean isScam() {
        return isScam;
    }
}