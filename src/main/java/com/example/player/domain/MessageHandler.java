package com.example.player.domain;

import com.example.player.factory.MessageStrategyFactory;
import com.example.player.interfaces.MessageStrategy;
import com.example.player.logger.Logger;
import com.example.player.model.Message;

/**
 * MessageHandler is responsible for processing messages received by a Player.
 * <p>
 * Responsibilities:
 * 1. Increment the Player's received message count and to maintain accurate state.
 * 2. Log each received message along with the updated count.
 * 3. Delegate role-specific reply logic to a MessageStrategy
 * (InitiatorStrategy or ResponderStrategy) based on the Player's role.
 * 4. Send reply messages via the Player's sendMessage method.
 * 5. Trigger a STOP message when messaging is complete (for Initiator role).
 * 6. Decouple message processing logic from the Player's thread to
 * keep threading concerns separate from message handling.
 * <p>
 * Design notes:
 * - Uses the Strategy pattern to separate role-specific behaviors, allowing
 * new roles to be added without modifying this class.
 * - Ensures a single source of truth for received message count.
 */
public class MessageHandler {

    private final Player player;
    private final MessageStrategy strategy;

    public MessageHandler(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        this.player = player;
        this.strategy = MessageStrategyFactory.determineStrategy(player);
    }

    public void handleMessage(Message message) throws InterruptedException {

        if (message == null || message.getContent() == null) {
            Logger.log("MessageHandler",
                    "Received either null message or no content, sent the stop message.");
            player.sendStopMessage();
            return;
        }
        int receivedCount = player.incrementReceivedCount();
        Logger.log(player.getName(),
                "Received From " + message.getSender() +
                        ": " + message.getContent() +
                        " (received#" + receivedCount + ")");

        strategy.handleMessage(player, message);
    }
}

