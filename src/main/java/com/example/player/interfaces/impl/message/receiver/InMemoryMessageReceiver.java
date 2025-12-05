package com.example.player.interfaces.impl.message.receiver;

import com.example.player.logger.Logger;
import com.example.player.model.Message;
import com.example.player.interfaces.MessageReceiver;

import java.util.concurrent.BlockingQueue;

/**
 * InMemoryMessageReceiver receives messages for a player using an in-memory queue.
 * <p>
 * Responsibilities:
 * 1. Receive messages asynchronously:
 * - Provide a method to fetch messages from a BlockingQueue.
 * - Block the caller until a message becomes available if the queue is empty.
 * <p>
 * 2. Decouple message transport:
 * - Abstract the message source from the rest of the system via the MessageReceiver interface.
 * - Allow easy substitution with other MessageReceiver implementations without changing player logic.
 * <p>
 * 3. Thread-safety:
 * - Rely on the thread-safe BlockingQueue to handle concurrent access from multiple producers and consumers.
 */
public class InMemoryMessageReceiver implements MessageReceiver {

    private final BlockingQueue<Message> inbox;

    public InMemoryMessageReceiver(BlockingQueue<Message> inbox) {

        if (inbox == null) {
            throw new IllegalArgumentException("Inbox cannot be null");
        }
        this.inbox = inbox;
    }

    @Override
    public Message receiveMessage() throws InterruptedException {
        Message msg = inbox.take();
        Logger.log("Receiver", "Received message from inbox: " + msg);
        return msg;
    }
}

