package com.fraud.behaviour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import com.fraud.behaviour.dto.BehaviorFeatureResponse;
import com.fraud.behaviour.entity.CallLog;
import com.fraud.behaviour.entity.Contact;
import com.fraud.behaviour.entity.Message;
import com.fraud.behaviour.repository.CallLogRepository;
import com.fraud.behaviour.repository.ContactRepository;
import com.fraud.behaviour.repository.MessageRepository;

@Service
public class BehaviorFeatureService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CallLogRepository callLogRepository;

    public BehaviorFeatureResponse extractFeatures(String senderPhone) {
        Optional<Contact> optionalContact = contactRepository.findByPhoneNumber(senderPhone);

        if (optionalContact.isEmpty()) {
            // Return default values if no contact found
            return new BehaviorFeatureResponse(false, 100, 1, 0.2);
        }

        Contact contact = optionalContact.get();

        // 1. Is contact saved
        boolean isSaved = contact.isSaved();

        // 2. Last interaction timestamp
        LocalDateTime latestTimestamp = null;

        List<Message> messages = messageRepository.findByContact(contact);
        List<CallLog> calls = callLogRepository.findByContact(contact);

        for (Message msg : messages) {
            if (latestTimestamp == null || msg.getTimestamp().isAfter(latestTimestamp)) {
                latestTimestamp = msg.getTimestamp();
            }
        }

        for (CallLog call : calls) {
            if (latestTimestamp == null || call.getTimestamp().isAfter(latestTimestamp)) {
                latestTimestamp = call.getTimestamp();
            }
        }

        int lastInteractionDaysAgo = 100; // default

        if (latestTimestamp != null) {
            Duration duration = Duration.between(latestTimestamp, LocalDateTime.now());
            lastInteractionDaysAgo = (int) duration.toDays();
        }

        // 3. Communication channel count (SMS, WhatsApp, Calls)
        Set<String> channels = new HashSet<>();

        for (Message msg : messages) {
            channels.add(msg.getSourceType().toLowerCase()); // "sms", "whatsapp"
        }

        if (!calls.isEmpty()) {
            channels.add("call");
        }

        int channelCount = channels.size();

        // 4. Compute behavioralScore (weighting example)
        double score = 0.0;

        if (isSaved) score += 0.4;
        if (lastInteractionDaysAgo < 7) score += 0.3;
        if (channelCount >= 2) score += 0.3;

        return new BehaviorFeatureResponse(
                isSaved,
                lastInteractionDaysAgo,
                channelCount,
                Math.min(score, 1.0)
        );
    }
}
