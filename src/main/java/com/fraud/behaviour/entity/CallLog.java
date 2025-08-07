package com.fraud.behaviour.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;


@Entity
@Table(name = "calls")
public class CallLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long callId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id") 
    private Contact contact;

    private LocalDateTime timestamp;

    private Integer duration;

    // Getters and setters
     public LocalDateTime getTimestamp(){
        return timestamp;
    }

}
