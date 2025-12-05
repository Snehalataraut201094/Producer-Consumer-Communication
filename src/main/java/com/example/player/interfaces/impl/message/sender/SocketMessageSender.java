package com.example.player.interfaces.impl.message.sender;

import com.example.player.interfaces.MessageSender;
import com.example.player.logger.Logger;
import com.example.player.model.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * SocketMessageSender sends messages to a peer over a TCP socket.
 * <p>
 * Responsibilities:
 * 1. Send messages over the network:
 * - Serialize Message objects using ObjectOutputStream.
 * - Flush the stream to ensure timely delivery.
 * <p>
 * 2. Handle network errors:
 * - Catch IOExceptions and other exceptions during sending.
 * - Translate failures into InterruptedException for consistent handling.
 * <p>
 * 3. Decouple message transport:
 * - Implement the MessageSender interface to allow seamless replacement of transport mechanisms.
 * - Hide socket-specific details from the rest of the system.
 * <p>
 * 4. Thread-safety considerations:
 * - ObjectOutputStream is used in a single-threaded context per sender to avoid concurrency issues.
 */
public class SocketMessageSender implements MessageSender {

    private final ObjectOutputStream out;

    public SocketMessageSender(Socket socket) throws Exception {
        if (socket == null) {
            throw new IllegalArgumentException("Socket cannot be null");
        }
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public synchronized void sendMessage(Message message) throws InterruptedException {
        try {
            if (message == null) {
                throw new IllegalArgumentException("Cannot send null message");
            }
            out.writeObject(message);
            Logger.log("SocketMessageSender", "Sent message: " + message);
            out.flush();
        } catch (IOException e) {
            throw new InterruptedException("Socket send failed: " + e.getMessage());
        }
    }
}

