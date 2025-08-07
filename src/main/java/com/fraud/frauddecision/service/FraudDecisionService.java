package com.fraud.frauddecision.service;

import org.springframework.stereotype.Service;

import com.fraud.frauddecision.dto.FraudDecisionRequest;
import com.fraud.frauddecision.dto.FraudDecisionResponse;

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
        event.setIsScam(isScam);
        event.setTimestamp(LocalDateTime.now());
        fraudResultProducer.send(event);

        return new FraudDecisionResponse(finalScore, isScam);
    }
}
