package com.example.player.factory;

import com.example.player.domain.Player;
import com.example.player.interfaces.MessageStrategy;
import com.example.player.interfaces.impl.message.strategy.InitiatorMessageStrategy;
import com.example.player.interfaces.impl.message.strategy.ResponderMessageStrategy;

public class MessageStrategyFactory {

   public static MessageStrategy determineStrategy(Player player) {
        if (player.isPlayerInitiator()) {
            return new InitiatorMessageStrategy();
        }
        return new ResponderMessageStrategy();
    }
}
