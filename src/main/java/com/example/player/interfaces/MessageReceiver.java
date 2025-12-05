package com.example.player.interfaces;

import com.example.player.model.Message;

/**
 * MessageReceiver defines the abstraction for receiving messages.
 * <p>
 * Responsibilities:
 * 1. Provide a generic interface to receive Message objects.
 * 2. Decouple PlayerThread from the underlying transport mechanism
 * (in-memory queue or socket).
 */
public interface MessageReceiver {
    Message receiveMessage() throws InterruptedException;
}

