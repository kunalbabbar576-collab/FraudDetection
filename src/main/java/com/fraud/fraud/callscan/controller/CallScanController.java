package com.fraud.fraud.callscan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraud.fraud.callscan.dto.CallScanRequest;
import com.fraud.fraud.callscan.dto.CallScanResponse;
import com.fraud.fraud.callscan.service.CallScanService;

@RestController
@RequestMapping("/api/v1/call")
public class CallScanController {

    @Autowired
    private CallScanService callScanService;

    @PostMapping("/predict")
    public ResponseEntity<CallScanResponse> scan(@RequestBody CallScanRequest request) {
        return ResponseEntity.ok(callScanService.scan(request.getTranscript()));
    }
}