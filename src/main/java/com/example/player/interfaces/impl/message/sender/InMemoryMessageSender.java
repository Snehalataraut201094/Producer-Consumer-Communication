package com.example.player.interfaces.impl.message.sender;

import com.example.player.model.Message;
import com.example.player.interfaces.MessageSender;

import java.util.concurrent.BlockingQueue;

/**
 * InMemoryMessageSender sends messages to a peer using an in-memory queue.
 * <p>
 * Responsibilities:
 * 1. Send messages asynchronously:
 * - Provide a method to place messages into a peer’s BlockingQueue.
 * - Block the caller if the queue is full, ensuring reliable delivery.
 * <p>
 * 2. Decouple message transport:
 * - Abstract the message destination via the MessageSender interface.
 * - Allow easy substitution with other MessageSender implementations without changing player logic.
 * <p>
 * 3. Thread-safety:
 * - Rely on the thread-safe BlockingQueue to handle concurrent access from multiple senders and receivers.
 */
public class InMemoryMessageSender implements MessageSender {

    private final BlockingQueue<Message> peerInbox;

    public InMemoryMessageSender(BlockingQueue<Message> peerInbox) {
        if (peerInbox == null) {
            throw new IllegalArgumentException("Peer inbox cannot be null");
        }
        this.peerInbox = peerInbox;
    }

    @Override
    public void sendMessage(Message message) throws InterruptedException {
        if (message == null) {
            throw new IllegalArgumentException("Cannot send null message");
        }
        peerInbox.put(message);
    }
}

