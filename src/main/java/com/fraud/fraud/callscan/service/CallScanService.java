package com.fraud.fraud.callscan.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraud.fraud.callscan.dto.CallScanResponse;
import com.fraud.fraud.textscan.client.LLMClient;

@Service
public class CallScanService {

    @Autowired
    private LLMClient llmClient;

    public CallScanResponse scan(String transcript) {
        Map<String, String> req = Map.of("message", transcript);
        double score = llmClient.getScore(req).get("score");
        boolean isScam = score > 0.65;
        return new CallScanResponse(score, isScam);
    }
}