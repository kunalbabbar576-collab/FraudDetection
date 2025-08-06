package com.fraud.behaviour.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    //Many rows in this table relate to one row in another table.
    //1 contact can send or receive many messages
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    private LocalDateTime timestamp;

    private String content;

    private String sourceType; // SMS or WHATSAPP

    // Getters and setters
    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public String getSourceType(){
        return sourceType;
    }


    
}