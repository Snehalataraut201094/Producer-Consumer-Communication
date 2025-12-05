package com.example.player.model;

import com.example.player.enums.MessageType;

import java.io.Serializable;

/**
 * The Message class represents a serializable communication unit containing a sender,
 * message content, and message type. It provides factory methods to create normal
 * and stop messages, encapsulating message creation logic and ensuring message
 * integrity across the communication system.
 */
public class Message implements Serializable {

    private final String sender;
    private final String content;
    private final MessageType messageType;

    private Message(String sender, String content, MessageType messageType) {
        this.sender = sender;
        this.content = content;
        this.messageType = messageType;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public static Message sendNormalMessage(String sender, String content) {
        return new Message(sender, content, MessageType.NORMAL);
    }

    public static Message sendStopMessage(String sender) {
        return new Message(sender, "STOP", MessageType.STOP);
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}

