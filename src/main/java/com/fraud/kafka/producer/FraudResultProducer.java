package com.fraud.kafka.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.fraud.frauddecision.dto.FraudResultEvent;

@Component
public class FraudResultProducer{
    private static final String TOPIC = "fraud-results";

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void publishResult(FraudResultEvent resultEvent) {
        kafkaTemplate.send(TOPIC, resultEvent);
    }

}
