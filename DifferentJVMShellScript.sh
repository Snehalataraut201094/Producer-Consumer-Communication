#!/bin/bash
# Filename: ShellScript.sh
# Purpose: Compile and run ResponderServer and InitiatorClient
# Server listens on port 5000

set -e

# -----------------------------------------
# 1. Compile Java files
# -----------------------------------------
echo "Compiling Java files..."

FILES=$(find src/main/java -name "*.java")
mkdir -p target/classes

if javac -d target/classes -sourcepath src/main/java $FILES; then
    echo "Compilation successful!"
else
    echo "Compilation failed. Exiting..."
    exit 1
fi

# -----------------------------------------
# 2. Start ResponderServer in background
# -----------------------------------------

echo "Starting ResponderServer in background..."

java -cp target/classes com.example.player.ResponderServer &
SERVER_PID=$!

# -----------------------------------------
# 3. Wait for server to listen on port 5000
# -----------------------------------------

echo "Waiting for server to start..."

sleep 5

echo "Server is up!"

# -----------------------------------------
# 4. Start InitiatorClient
# -----------------------------------------

echo "Starting InitiatorClient..."

java -cp target/classes com.example.player.InitiatorClient

# -----------------------------------------
# 5. Stop server if still running
# -----------------------------------------

if kill -0 $SERVER_PID 2>/dev/null; then
    kill $SERVER_PID
    echo "Server stopped."
else
    echo "ResponderServer already exited naturally."
fi

echo "All done."