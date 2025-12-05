# Requirements
This project implements a Player class that allows communication between two instances of the class. The communication is based on message passing, where each player sends and receives messages to/from the other player in a structured sequence.

## Functional Requirements
1. Create Two Players: The system should allow the creation of two player instances.

## Message Passing:
1. One of the players (referred to as the initiator) sends a message to the second player.
2. When a player receives a message, they should send back a response. The response message should be the received message concatenated with a message counter, representing the number of messages the player has sent.

## Stop Condition:
The communication should continue until the initiator has sent and received 10 messages. The program should gracefully terminate after this condition is met.

## Single Java Process:
Initially, both players should run within the same Java process (i.e., the same PID). This ensures that the players share the same memory space and can communicate using threads or other Java concurrency mechanisms.

## Documentation:
Every class should be thoroughly documented, detailing the responsibilities and functionality of each component to ensure code clarity.

## Future Extension:
As an extension of the task, it should be possible to run each player in a separate Java process to simulate a more distributed environment.

## Technical Requirement
1. Java: The solution should be implemented using pure Java (no additional frameworks such as Spring or libraries outside of the standard Java API).
2. Design Focus: The primary focus should be on designing a clean, clear, and maintainable solution. The technology stack should be as simple as possible while ensuring the system works correctly and meets the  requirements.
