#!/bin/sh

rm ./src/*.class
javacc -OUTPUT_DIRECTORY=./src ./src/ParserL0.jj
javac ./src/*.java
