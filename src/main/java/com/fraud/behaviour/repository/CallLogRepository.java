package com.fraud.behaviour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fraud.behaviour.entity.CallLog;
import com.fraud.behaviour.entity.Contact;

public interface CallLogRepository extends JpaRepository<CallLog, Long> {

    // Get all calls for a given contact
    List<CallLog> findByContact(Contact contact);
}