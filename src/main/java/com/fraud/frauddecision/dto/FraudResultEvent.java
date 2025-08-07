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
}
