package com.fraud.frauddecision.dto;

public class FraudDecisionRequest {
    private String message;
    private String channel;
    private double llmScore;
    private double behavioralScore;

    public double getLlmScore() {
        return llmScore;
    }

    public void setLlmScore(double llmScore) {
        this.llmScore = llmScore;
    }

    public double getBehavioralScore() {
        return behavioralScore;
    }

    public void setBehavioralScore(double behavioralScore) {
        this.behavioralScore = behavioralScore;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public String getChannel(){
        return channel;
    }
    public void setChannel(String channel){
        this.channel=channel;
    }
}