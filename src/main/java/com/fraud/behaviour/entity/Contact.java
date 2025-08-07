package com.fraud.behaviour.entity;

import java.util.List;


import jakarta.persistence.*;



@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    private Long contactId;

    private String phoneNumber;

    private boolean isSaved;

    
    // ✅ Messages sent/received by this contact
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;

    // ✅ Calls made to/from this contact
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CallLog> calls;

    public boolean isSaved() {
        return isSaved;
    }
}