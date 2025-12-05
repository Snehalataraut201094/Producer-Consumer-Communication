package com.example.player.interfaces;

import com.example.player.domain.Player;
import com.example.player.model.Message;

/**
 * MessageStrategy defines the contract for role-specific message-handling logic
 * in a Player messaging system.
 * <p>
 * Responsibilities:
 * 1. Provide a uniform interface for handling messages based on Player roles.
 * 2. Encapsulate role-specific behavior (e.g., Initiator vs Responder) separately
 * from the MessageHandler.
 * 3. Allow MessageHandler to delegate message-processing decisions without knowing
 * the details of each role.
 * <p>
 * Design notes:
 * - Follows the Strategy design pattern to enable Open/Closed Principle compliance.
 * - Each implementation (e.g., InitiatorStrategy, ResponderStrategy) defines its
 * own handling logic for sending replies or triggering STOP messages.
 * - Keeps MessageHandler focused on coordination, logging, and state updates,
 * while strategy implementations focus solely on role-specific behaviors.
 */
public interface MessageStrategy {
    void handleMessage(Player player, Message message) throws InterruptedException;
}
