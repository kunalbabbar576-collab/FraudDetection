package com.fraud.frauddecision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraud.frauddecision.dto.FraudDecisionRequest;
import com.fraud.frauddecision.dto.FraudDecisionResponse;
import com.fraud.frauddecision.service.FraudDecisionService;

@RestController
@RequestMapping("/api/v1/decision")
public class FraudDecisionController {

    @Autowired
    private FraudDecisionService fraudDecisionService;

    @PostMapping("/evaluate")
    public ResponseEntity<FraudDecisionResponse> evaluate(@RequestBody FraudDecisionRequest request) {
        return ResponseEntity.ok(fraudDecisionService.evaluate(request));
    }
}
