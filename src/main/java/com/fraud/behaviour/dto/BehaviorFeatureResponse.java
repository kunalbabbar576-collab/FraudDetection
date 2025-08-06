package com.fraud.behaviour.dto;


public class BehaviorFeatureResponse {
    private boolean contactIsSaved;
    private int lastInteractionDaysAgo;
    private int communicationChannelCount;
    private double behavioralScore;

    public BehaviorFeatureResponse(boolean contactIsSaved, int lastInteractionDaysAgo,
                                   int communicationChannelCount, double behavioralScore) {
        this.contactIsSaved = contactIsSaved;
        this.lastInteractionDaysAgo = lastInteractionDaysAgo;
        this.communicationChannelCount = communicationChannelCount;
        this.behavioralScore = behavioralScore;
    }

    public boolean isContactIsSaved() {
        return contactIsSaved;
    }

    public int getLastInteractionDaysAgo() {
        return lastInteractionDaysAgo;
    }

    public int getCommunicationChannelCount() {
        return communicationChannelCount;
    }

    public double getBehavioralScore() {
        return behavioralScore;
    }
}