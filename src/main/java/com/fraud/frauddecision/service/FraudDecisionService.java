package com.fraud.frauddecision.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraud.frauddecision.dto.FraudDecisionRequest;
import com.fraud.frauddecision.dto.FraudDecisionResponse;
import com.fraud.frauddecision.dto.FraudResultEvent;
import com.fraud.kafka.producer.FraudResultProducer;

@Service
public class FraudDecisionService {

    @Autowired
    private FraudResultProducer fraudResultProducer;

    public FraudDecisionResponse evaluate(FraudDecisionRequest request) {
        double finalScore = 0.7 * request.getLlmScore() + 0.3 * request.getBehavioralScore();
        boolean isScam = finalScore > 0.65;

        FraudResultEvent event = new FraudResultEvent();

        event.setMessage(request.getMessage());
        event.setChannel(request.getChannel());
        event.setLlmScore(request.getLlmScore());
        event.setBehavioralScore(request.getBehavioralScore());
        event.setFinalScore(finalScore);
        event.setScam(isScam);
        event.setTimestamp(LocalDateTime.now());
        fraudResultProducer.publishResult(event);

        return new FraudDecisionResponse(finalScore, isScam);
    }
}
