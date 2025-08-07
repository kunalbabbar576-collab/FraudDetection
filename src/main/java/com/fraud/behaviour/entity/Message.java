package com.fraud.behaviour.entity;

import java.time.LocalDateTime;


import jakarta.persistence.*;


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