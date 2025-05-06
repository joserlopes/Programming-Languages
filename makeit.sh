#!/bin/sh

rm ./src/main/java/L1/*.class
javacc -OUTPUT_DIRECTORY=./src/main/java/L1/ ./src/main/java/L1/ParserL0.jj
javac ./src/main/java/L1/*.java
