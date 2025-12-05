#!/bin/bash
# Filename: SingleJVMShell.sh
# Purpose: Compile and run main class in single JVM

set -e

echo "==================================================="
echo "Compiling and running SingleJVMMain.java"
echo "==================================================="

# 1. Create output directory

mkdir -p target/classes

# 2. Compile Java source files

echo "Compiling Java files..."
javac -d target/classes $(find src/main/java -name "*.java")
echo "Compilation successful!"

# 3. Run the main class

echo "Running SingleJVMMain..."

java -cp target/classes com.example.player.SingleJVMMain

