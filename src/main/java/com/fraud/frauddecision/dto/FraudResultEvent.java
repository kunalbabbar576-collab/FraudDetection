package com.fraud.frauddecision.dto;

import java.time.LocalDateTime;

public class FraudResultEvent {
    private String conversation;
    private String channel;
    private double llmScore;
    private double behavioralScore;
    private double finalScore;
    private boolean isScam;
    private LocalDateTime timestamp;

    public void setMessage(String conversation) {
        this.conversation = conversation;
    }

    public String getConversation() {
        return conversation;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public void setLlmScore(double llmScore) {
        this.llmScore = llmScore;
    }

    public double getLlmScore() {
        return llmScore;
    }

    public void setBehavioralScore(double behavioralScore) {
        this.behavioralScore = behavioralScore;
    }

    public double getBehavioralScore() {
        return behavioralScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setScam(boolean isScam) {
        this.isScam = isScam;
    }

    public boolean isScam() {
        return isScam;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
}
}
