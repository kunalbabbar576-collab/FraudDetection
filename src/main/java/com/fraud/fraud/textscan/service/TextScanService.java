package com.fraud.fraud.textscan.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraud.fraud.textscan.client.LLMClient;
import com.fraud.fraud.textscan.dto.ScanResponse;

@Service
public class TextScanService {
    @Autowired
    private LLMClient llmClient;

    public ScanResponse scan(String message) {
        Map<String, String> req = Map.of("message", message);
        double score = llmClient.getScore(req).get("score");
        boolean isScam = score > 0.65;
        return new ScanResponse(score, isScam);
    }
}
