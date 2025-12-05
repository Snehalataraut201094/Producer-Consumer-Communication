package com.example.player;

import com.example.player.domain.Player;
import com.example.player.domain.PlayerThread;
import com.example.player.interfaces.impl.message.receiver.InMemoryMessageReceiver;
import com.example.player.interfaces.impl.message.sender.InMemoryMessageSender;
import com.example.player.logger.Logger;

public class SingleJVMMain {
    public static void main(String[] args) throws InterruptedException {

        try {
            Player firstPlayer = new Player("Player1", true, 2);
            Player secondPlayer = new Player("Player2", false, 0);

            // Link in-memory queues
            firstPlayer.setPeerSender(new InMemoryMessageSender(secondPlayer.getInbox()));
            secondPlayer.setPeerSender(new InMemoryMessageSender(firstPlayer.getInbox()));

            PlayerThread t1 = new PlayerThread(firstPlayer, new InMemoryMessageReceiver(firstPlayer.getInbox()));
            PlayerThread t2 = new PlayerThread(secondPlayer, new InMemoryMessageReceiver(secondPlayer.getInbox()));

            t1.start();
            t2.start();

            // Initiator sends the first message
            firstPlayer.sendMessage("Hello Player2!");

            t1.join();
            t2.join();

        } catch (Exception ex) {
            Logger.log("The exception occurred while executing both threads", ex.getMessage());
        }
    }
}

