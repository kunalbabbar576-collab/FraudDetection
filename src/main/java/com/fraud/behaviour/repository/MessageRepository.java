package com.fraud.behaviour.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fraud.behaviour.entity.Contact;
import com.fraud.behaviour.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Get all messages for a given contact
    List<Message> findByContact(Contact contact);
}