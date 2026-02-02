# Producer-Consumer-Communication
The Project included the producer and consumer communication using Java Multithreading, OOPS and SOLID Design Principle.

# Requirements
This project implements a Player class that allows communication between two instances of the class. The communication is based on message passing, where each player sends and receives messages to/from the other player in a structured sequence.

## Functional Requirements
1. Create Two Players: The system should allow the creation of two player instances.
2. Both the player should run within the same java process.
3. Every player should be in separate JVM.

## Message Passing:
1. One of the players (referred to as the initiator) sends a message to the second player.
2. When a player receives a message, they should send back a response. The response message should be the received message concatenated with a message counter, representing the number of messages the player has sent.

## Stop Condition:
The communication should continue until the initiator has sent and received 10 messages. The program should gracefully terminate after this condition is met.

## Single Java Process:
Initially, both players should run within the same Java process (i.e., the same PID). This ensures that the players share the same memory space and can communicate using threads or other Java concurrency mechanisms.

## Different JVM:
As an additional requirement it should be possible to run each player in a separate Java Virtual Machine (JVM).
This means that each player will run as an independent process,and they will communicate through inter - process communication mechanisms such as sockets or IPC methods.

## Documentation:
Every class should be thoroughly documented, detailing the responsibilities and functionality of each component to ensure code clarity.

## Future Extension:
As an extension of the task, it should be possible to run each player in a separate Java process to simulate a more distributed environment.

## Technical Requirement
1. Java: The solution should be implemented using pure Java (no additional frameworks such as Spring or libraries outside the standard Java API).
2. Design Focus: The primary focus should be on designing a clean, clear, and maintainable solution. The technology stack should be as simple as possible while ensuring the system works correctly and meets the  requirements.

## Followed Design Principles:
1. OOPS Principles: The design adhere to Object-Oriented Programming principles, ensuring encapsulation, inheritance, and polymorphism are appropriately applied.
2. SOLID Principles: The design followed SOLID principles to ensure that the code is modular, maintainable, and extensible.
3. Separation of Concerns: Different functionalities are separated into distinct classes or modules to enhance code readability and maintainability.
4. Thread Safety: Since the players will be communicating in a multi-threaded environment, appropriate synchronization mechanisms implemented to ensure thread safety.
5. Error Handling: The system included robust error handling to manage potential communication failures or unexpected conditions gracefully.
6. Logging: Implemented logging to track the communication process and any issues that arise during execution.
7. Extensibility: Designed the system in a way that allows for easy extension or modification in the future, such as adding new message types or changing the communication protocol.
8. Performance: While the primary focus is on design and maintainability, consider the performance implications of the communication mechanism chosen, especially if extended to separate JVMs in the future.
9. Documentation: Provided comprehensive documentation for the codebase, including class descriptions, method explanations, and usage instructions to facilitate understanding and future maintenance.
10. Code Quality: Ensured that the code adheres to standard coding conventions and best practices to enhance readability and maintainability.
11. Version Control: Used a version control system (e.g., Git) to manage changes to the codebase, allowing for collaboration and tracking of modifications over time.

## How to Run
1. Clone the repository(https://github.com/Snehalataraut201094/Producer-Consumer-Communication.git) to your local machine.
2. Navigate to the project directory.
3. Compile the Java files using the command:
   ```
   javac *.java
   ```
4. Run the Main class using the command:
   ```
    java Main
    ```
5. Observe the console output to see the message exchange between the two players.
6. The program will terminate automatically after the initiator has sent and received 10 messages.
7. To run each player in a separate JVM, you run seperate command prompt for each player and run them independently, ensuring they communicate through sockets or another IPC mechanism.

## Example Output
```
Player 1: Sending message 1
Player 2: Received message 1
Player 2: Sending response 1
Player 1: Received response 1
Player 1: Sending message 2
Player 2: Received message 2
Player 2: Sending response 2
Player 1: Received response 2
...
Player 1: Sending message 10
Player 2: Received message 10
Player 2: Sending response 10
Player 1: Received response 10
Communication ended after 10 messages.
```
## Output From IDE with number of message from Intitiator is 1:

#### [Thread-Player1] Player1: Thread started
#### [Thread-Player2] Player2: Thread started
#### [main] Player1: SENT MESSAGE-> Hello Player2! [sent#1]
#### [main] Player1: Waiting for thread to terminate...
#### [Thread-Player2] Receiver: Received message from inbox: Message{sender='Player1', content='Hello Player2! [sent#1]', messageType=NORMAL}
#### [Thread-Player2] Player2: Received From Player1: Hello Player2! [sent#1] (received#1)
#### [Thread-Player2] Player2: Replying with message: Hello Player2! [sent#1] | reply-from-Player2
#### [Thread-Player2] Player2: SENT MESSAGE-> Hello Player2! [sent#1] | reply-from-Player2 [sent#1]
#### [Thread-Player1] Receiver: Received message from inbox: Message{sender='Player2', content='Hello Player2! [sent#1] | reply-from-Player2 [sent#1]', messageType=NORMAL}
#### [Thread-Player1] Player1: Received From Player2: Hello Player2! [sent#1] | reply-from-Player2 [sent#1] (received#1)
#### [Thread-Player1] Player1: Reached max messages, sending STOP...
#### [Thread-Player1] Player1: SENT STOP message
#### [Thread-Player1] Receiver: Received message from inbox: Message{sender='Player1', content='STOP', messageType=STOP}
#### [Thread-Player1] Player1: STOP received -> exiting
#### [Thread-Player1] Player1: SENT STOP message
#### [Thread-Player2] Receiver: Received message from inbox: Message{sender='Player1', content='STOP', messageType=STOP}
#### [Thread-Player2] Player2: STOP received -> exiting
#### [Thread-Player2] Player2: SENT STOP message
#### [Thread-Player1] Player1: Thread terminated. Message Sent =1
#### [Thread-Player2] Player2: Thread terminated. Message Sent =1
#### [main] Player2: Waiting for thread to terminate...

## Note
- Ensure that your Java environment is set up correctly to compile and run the code.
- Modify the code as needed to fit your specific requirements or environment.
- Feel free to reach out if you have any questions or need further assistance with the implementation.
- This project is intended for educational purposes to demonstrate inter-thread and inter-process communication in Java.
- Contributions and improvements to the codebase are welcome!


- Happy Coding!
- Author: Snehalata raut
