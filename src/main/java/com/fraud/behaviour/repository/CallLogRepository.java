package com.fraud.behaviour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fraud.behaviour.entity.CallLog;
import com.fraud.behaviour.entity.Contact;

@Repository
public interface CallLogRepository extends JpaRepository<CallLog, Long> {

    // Get all calls for a given contact
    List<CallLog> findByContact(Contact contact);
}