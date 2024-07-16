#!/bin/bash

# Variables
GROUP_ID="com.tccompany"
ARTIFACT_ID="filter-generation"
VERSION="1.0.0"

# Navigate to the project directory
cd ./backend-libs/filter-generation

# Build the JAR file
mvn clean package

# Print the contents of the target directory
ls target

# Install the JAR file into the local Maven repository
mvn install:install-file -Dfile=target/${ARTIFACT_ID}-${VERSION}.jar -DgroupId=${GROUP_ID} -DartifactId=${ARTIFACT_ID} -Dversion=${VERSION} -Dpackaging=jar
