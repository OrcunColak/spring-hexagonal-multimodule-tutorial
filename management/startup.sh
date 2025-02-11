#!/bin/bash

# Get the directory of the script
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Set the configuration directory
CONFIG_DIR="$SCRIPT_DIR/config"

# Set the JAR file path
JAR_FILE="$SCRIPT_DIR/management.jar"

# Run the Java application with the specified configuration
java -Dspring.config.location="file://$CONFIG_DIR/" -jar "$JAR_FILE"
