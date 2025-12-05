package com.example.player.domain;

import com.example.player.interfaces.MessageSender;
import com.example.player.logger.Logger;
import com.example.player.model.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Player represents a participant in the messaging system.
 * <p>
 * Responsibilities:
 * 1. Maintain player identity:
 * - Store the player’s name.
 * - Determine whether the player is the initiator or responder.
 * - Keep the maximum number of messages the player can send.
 * <p>
 * 2. Track messaging state:
 * - Maintain thread-safe counters for messages sent and received.
 * - Provide methods to increment and retrieve these counters.
 * <p>
 * 3. Handle message delivery:
 * - Hold an inbox (BlockingQueue) for receiving messages asynchronously.
 * - Send messages to a peer via the MessageSender abstraction.
 * - Send STOP messages to signal the end of communication.
 * <p>
 * 4. Ensure thread-safety:
 * - Use AtomicInteger for counters to allow concurrent updates without locks.
 * - Use a thread-safe BlockingQueue for the inbox to allow asynchronous message receipt.
 * <p>
 * 5. Decouple from infrastructure:
 * - Depend on the MessageSender interface, allowing different message transport implementations.
 * - Logging is delegated to a Logger utility for observability.
 * <p>
 * 6. Support graceful termination:
 * - Ensure STOP messages are sent to both peer and own inbox.
 */
public class Player {

    private static final int CAPACITY = 20;
    private final String name;
    private final boolean isPlayerInitiator;
    private final int maxMessages;
    private final BlockingQueue<Message> inbox = new LinkedBlockingQueue<>(CAPACITY);
    private final AtomicInteger sentCount = new AtomicInteger(0);
    private final AtomicInteger receivedCount = new AtomicInteger(0);

    private MessageSender peerSender;

    public Player(String name, boolean initiator, int maxMessages) {
        this.name = name;
        this.isPlayerInitiator = initiator;
        this.maxMessages = maxMessages;
    }

    public String getName() {
        return name;
    }

    public boolean isPlayerInitiator() {
        return isPlayerInitiator;
    }

    public int getMaxMessages() {
        return maxMessages;
    }

    public int getSentCount() {
        return sentCount.get();
    }

    public int getReceivedCount() {
        return receivedCount.get();
    }

    public BlockingQueue<Message> getInbox() {
        return inbox;
    }

    public void setPeerSender(MessageSender sender) {
        if (this.peerSender != null) {
            throw new IllegalStateException("Peer sender already set for " + name);
        }
        this.peerSender = sender;
    }

    public int incrementReceivedCount() {
        return receivedCount.incrementAndGet();
    }

    public void sendMessage(String content) throws InterruptedException {
        if (peerSender == null) throw new IllegalStateException(name + " peer sender not set.");

        if (content == null || content.isEmpty()) {
            Logger.log(name, "Provided message is null or empty. Sending stop message.");
            sendStopMessage();
            return;
        }
        int counter = sentCount.incrementAndGet();
        Message msg = createNormalMessage(content, counter);
        peerSender.sendMessage(msg);
        Logger.log(name, "SENT MESSAGE-> " + msg.getContent());
    }

    public void sendStopMessage() throws InterruptedException {

        Message stopMessage = Message.sendStopMessage(name);

        if (peerSender != null) {
            peerSender.sendMessage(stopMessage);
        }
        inbox.put(stopMessage);
        Logger.log(name, "SENT STOP message");
    }

    private Message createNormalMessage(String content, int counter) {
        return Message.sendNormalMessage(name, content + " [sent#" + counter + "]");
    }
}

