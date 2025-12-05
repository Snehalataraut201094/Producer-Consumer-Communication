package com.example.player.interfaces.impl.message.strategy;

import com.example.player.domain.Player;
import com.example.player.interfaces.MessageStrategy;
import com.example.player.logger.Logger;
import com.example.player.model.Message;

/**
 * InitiatorStrategy handles the message-processing logic for a Player
 * with the role of Initiator.
 * <p>
 * Responsibilities:
 * 1. Determine whether the maximum number of messages has been reached its limit.
 * 2. Send a STOP message if the Player has completed messaging limit.
 * 3. Prepare and send reply messages if messaging limit is still not fulfilled.
 * 4. Encapsulate role-specific logic separately from MessageHandler to
 * achieve the Open/Closed Principle.
 * <p>
 * Design notes:
 * - Implements the MessageStrategy interface to allow delegation from MessageHandler.
 * - Focuses solely on Initiator-specific reply and termination logic.
 * - Does not increment the Player's received message count; this is handled
 * centrally by MessageHandler to maintain a single source of increment.
 */
public class InitiatorMessageStrategy implements MessageStrategy {

    public void handleMessage(Player player, Message message) throws InterruptedException {

        if (hasReachedMaxMessages(player)) {
            Logger.log(player.getName(), "Reached max messages, sending STOP...");
            player.sendStopMessage();
            return;
        }
        String reply = message.getContent() + " | reply-from-" + player.getName();
        Logger.log(player.getName(), "Replying with message: " + reply);

        player.sendMessage(reply);
    }

    private boolean hasReachedMaxMessages(Player player) {
        return player.getSentCount() >= player.getMaxMessages() &&
                player.getReceivedCount() >= player.getMaxMessages();
    }
}
