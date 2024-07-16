#!/bin/bash

# Navigate to the project directory
cd ./

# Build the project and package it into a JAR file
./gradlew build

# Publish the JAR to your local Maven repository
./gradlew publishToMavenLocal

echo "Build and publish to local Maven repository completed."