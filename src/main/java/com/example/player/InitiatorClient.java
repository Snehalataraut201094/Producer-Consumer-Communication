package com.example.player;

import com.example.player.domain.Player;
import com.example.player.domain.PlayerThread;
import com.example.player.interfaces.impl.message.receiver.SocketMessageReceiver;
import com.example.player.interfaces.impl.message.sender.SocketMessageSender;

import java.net.Socket;


/**
 * InitiatorClient is the main class for starting a Player as an Initiator in multi-JVM mode.
 * <p>
 * Responsibilities:
 * 1. Connect to the Responder server via a TCP socket.
 * 2. Create a Player instance in initiator mode.
 * 3. Link the Player with SocketMessageSender and SocketMessageReceiver.
 * 4. Start the PlayerThread to process incoming messages.
 * 5. Send the first message to start the conversation.
 */
public class InitiatorClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);

        Player initiator = new Player("Initiator", true, 2);
        initiator.setPeerSender(new SocketMessageSender(socket));

        PlayerThread t = new PlayerThread(initiator, new SocketMessageReceiver(socket));
        t.start();

        Thread.sleep(1000);
        // First message
        initiator.sendMessage("Hello Responder!");

        t.join();
    }
}

