package com.example.player.logger;

public class Logger {
    public static void log(String message, String playerName) {
        System.out.printf("[%s] %s: %s%n", Thread.currentThread().getName(), message, playerName);
    }

    public static void log(String message) {
        System.out.printf("%s%n", message);
    }
}
