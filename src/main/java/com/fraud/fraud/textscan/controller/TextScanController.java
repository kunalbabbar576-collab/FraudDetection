package com.fraud.fraud.textscan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraud.fraud.textscan.dto.ScanRequest;
import com.fraud.fraud.textscan.dto.ScanResponse;
import com.fraud.fraud.textscan.service.TextScanService;

@RestController
@RequestMapping("/api/v1/text")
public class TextScanController {
    @Autowired
    private TextScanService textScanService;

    @PostMapping("/predict")
    public ResponseEntity<ScanResponse> predict(@RequestBody ScanRequest scanRequest) {
        String message = scanRequest.getMessage();
        ScanResponse response = textScanService.scan(message);
        return ResponseEntity.ok(response);
    }
}
