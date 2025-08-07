package com.fraud.behaviour.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fraud.behaviour.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
    // Custom method to find contact by phone number
    Optional<Contact> findByPhoneNumber(String phoneNumber);
}
