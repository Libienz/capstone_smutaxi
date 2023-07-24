package com.capstone.smutaxi.entity;


import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;


@Entity
@Getter
@Table(name = "messages")
public class Message {

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderEmail;

    private String senderName;

    private String sendTime;

    private String message;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    public Message() {
    }
}
