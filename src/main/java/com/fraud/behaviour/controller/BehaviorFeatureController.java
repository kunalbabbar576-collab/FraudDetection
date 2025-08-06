package com.fraud.behaviour.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fraud.behaviour.dto.BehaviorFeatureResponse;
import com.fraud.behaviour.service.BehaviorFeatureService;

@RestController
@RequestMapping("/api/v1/behavior")
public class BehaviorFeatureController {

    @Autowired
    private BehaviorFeatureService behaviorFeatureService;

    @GetMapping("/features")
    public ResponseEntity<BehaviorFeatureResponse> getFeatures(@RequestParam String sender) {
        return ResponseEntity.ok(behaviorFeatureService.extractFeatures(sender));
    }
}