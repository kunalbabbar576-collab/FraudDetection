package com.fraud.behaviour.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fraud.behaviour.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    
    // Custom method to find contact by phone number
    Optional<Contact> findByPhoneNumber(String phoneNumber);
}
