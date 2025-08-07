package com.fraud.textscan.client;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "llmClient", url = "${llm.url}")
public interface LLMClient {
    @PostMapping("/predict")
    Map<String, Double> getScore(@RequestBody Map<String, String> request);
}
