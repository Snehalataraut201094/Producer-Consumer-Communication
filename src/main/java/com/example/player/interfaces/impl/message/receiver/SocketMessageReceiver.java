package com.example.player.interfaces.impl.message.receiver;

import com.example.player.interfaces.MessageReceiver;
import com.example.player.model.Message;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;


/**
 * SocketMessageReceiver receives messages from a peer over a TCP socket.
 * <p>
 * Responsibilities:
 * 1. Receive messages over the network:
 * - Deserialize Message objects using ObjectInputStream.
 * - Block until a message is available.
 * <p>
 * 2. Handle network errors:
 * - Catch IOExceptions, ClassNotFoundException, and other exceptions during reception.
 * - Translate failures into InterruptedException for consistent handling.
 * <p>
 * 3. Decouple message transport:
 * - Implement the MessageReceiver interface to allow seamless replacement of transport mechanisms.
 * - Hide socket-specific details from the rest of the system.
 * <p>
 * 4. Thread-safety considerations:
 * - ObjectInputStream is used in a single-threaded context per receiver to avoid concurrency issues.
 */
public class SocketMessageReceiver implements MessageReceiver {

    private final ObjectInputStream inputStream;

    public SocketMessageReceiver(Socket socket) throws IOException {

        if (socket == null) {
            throw new IllegalArgumentException("Socket cannot be null");
        }
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public Message receiveMessage() throws InterruptedException {
        try {
            return (Message) inputStream.readObject();
        } catch (EOFException | SocketException e) {
            throw new InterruptedException("Socket closed: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new InterruptedException("Socket receive failed: " + e.getMessage());
        }
    }
}

