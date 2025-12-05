package com.example.player;

import com.example.player.domain.Player;
import com.example.player.domain.PlayerThread;
import com.example.player.interfaces.impl.message.receiver.SocketMessageReceiver;
import com.example.player.interfaces.impl.message.sender.SocketMessageSender;
import com.example.player.logger.Logger;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * ResponderServer is the main class for starting a Player as a Responder in multi-JVM mode.
 * <p>
 * Responsibilities:
 * 1. Start a server socket and accept client connections.
 * 2. Create a Player instance in responder mode.
 * 3. Link the Player with SocketMessageSender and SocketMessageReceiver.
 * 4. Start the PlayerThread to process incoming messages.
 */
public class ResponderServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);

        Logger.log("Waiting for client...");
        Socket client = serverSocket.accept();
        Logger.log("Client connected!");

        Player responder = new Player("Responder", false, 0);
        responder.setPeerSender(new SocketMessageSender(client));
        PlayerThread t = new PlayerThread(responder, new SocketMessageReceiver(client));

        t.start();
        t.join();
    }
}

