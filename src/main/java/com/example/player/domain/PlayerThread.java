package com.example.player.domain;

import com.example.player.enums.MessageType;
import com.example.player.interfaces.MessageReceiver;
import com.example.player.logger.Logger;
import com.example.player.model.Message;

/**
 * PlayerThread manages a dedicated thread for a single Player in the messaging system.
 * <p>
 * Responsibilities:
 * 1. Manage thread lifecycle:
 * - Start a dedicated thread for the player.
 * - Stop the thread gracefully when a STOP message is received.
 * - Allow external code to wait for the thread to finish via join().
 * - The running variable controls the thread’s active state
 * <p>
 * 2. Handle messages:
 * - Continuously receive messages for the player from MessageReceiver.
 * - Delegate message processing to MessageHandler.
 * - Detect STOP messages and terminate processing gracefully.
 * <p>
 * 3. Logging and observability:
 * - Log thread start, interruption, STOP receipt, and termination events.
 * - Include player-specific information in logs (e.g., name, messages sent).
 * <p>
 * 4. Ensure thread-safety:
 * - Use synchronized methods for starting and joining threads.
 * - Use a volatile boolean flag to safely track running state across threads.
 * <p>
 * 5. Decouple processing from infrastructure:
 * - Message reception is handled by MessageReceiver.
 * - Message processing is handled by MessageHandler.
 * - Logging is delegated to Logger utility.
 * <p>
 * 6. Support graceful termination:
 * - Ensure thread stops on STOP messages or interruption.
 * - Clean up resources and mark running state as false when exiting.
 */
public class PlayerThread implements Runnable {

    private final Player player;
    private final MessageHandler handler;
    private final MessageReceiver receiver;
    private Thread thread;
    private volatile boolean running = false;

    public PlayerThread(Player player, MessageReceiver receiver) {
        this.player = player;
        this.handler = new MessageHandler(player);
        this.receiver = receiver;
    }

    @Override
    public void run() {
        Logger.log(player.getName(), "Thread started");
        try {
            while (running) {
                Message msg = receiver.receiveMessage();

                if (msg == null) {
                    Logger.log(player.getName(), "Received null message - exiting");
                    break;
                }
                if (msg.getMessageType() == MessageType.STOP) {
                    Logger.log(player.getName(), "STOP received -> exiting");
                    player.sendStopMessage();
                    break;
                }
                handler.handleMessage(msg);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.log(player.getName(), "Interrupted - exiting");
        } finally {
            running = false;
            Logger.log(player.getName(), "Thread terminated. Message Sent =" + player.getSentCount());
        }
    }

    public synchronized void start() {
        if (running) {
            Logger.log(player.getName(), "Thread already running, start() ignored");
            return;
        }
        thread = new Thread(this, "Thread-" + player.getName());
        running = true;
        thread.start();
    }

    public synchronized void join() throws InterruptedException {
        Logger.log(player.getName(), "Waiting for thread to terminate...");
        if (thread != null) thread.join();
    }
}

