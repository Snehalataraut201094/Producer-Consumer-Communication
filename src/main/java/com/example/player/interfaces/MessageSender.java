package com.example.player.interfaces;

import com.example.player.model.Message;


/**
 * MessageSender defines the abstraction for sending messages.
 * <p>
 * Responsibilities:
 * 1. Provide a generic interface to send Message objects.
 * 2. Decouple Player from the underlying transport mechanism
 * (in-memory queue or socket).
 */
public interface MessageSender {
    void sendMessage(Message message) throws InterruptedException;
}

