package com.example.player.interfaces.impl.message.strategy;

import com.example.player.domain.Player;
import com.example.player.interfaces.MessageStrategy;
import com.example.player.logger.Logger;
import com.example.player.model.Message;

/**
 * ResponderStrategy handles the message-processing logic for a Player
 * with the role of Responder.
 * <p>
 * Responsibilities:
 * 1. Prepare and send reply messages in response to received messages.
 * 2. Encapsulate Responder-specific reply logic separately from MessageHandler
 * to achieve the Open/Closed Principle.
 * <p>
 * Design notes:
 * - Implements the MessageStrategy interface to allow separation from MessageHandler.
 * - Focuses solely on Responder-specific reply behavior.
 * - Does not handle message counts or STOP messages; these concerns are managed
 * by MessageHandler.
 */
public class ResponderMessageStrategy implements MessageStrategy {

    @Override
    public void handleMessage(Player player, Message msg) throws InterruptedException {

        String reply = msg.getContent() + " | reply-from-" + player.getName();
        Logger.log(player.getName(), "Replying with message: " + reply);

        player.sendMessage(reply);
    }
}
