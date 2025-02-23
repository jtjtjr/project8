#!/bin/bash

# Variables
IMAGE_NAME="java-game"
CONTAINER_NAME="java-game-container"

# Build the Docker image
echo "Building Docker image..."
docker build -t $IMAGE_NAME .

# Check if a container with this name is already running, and stop/remove it
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "Stopping and removing existing container..."
    docker stop $CONTAINER_NAME
    docker rm $CONTAINER_NAME
fi

# Run the container in the background
echo "Starting container..."
docker run -d --name $CONTAINER_NAME $IMAGE_NAME

echo "Waiting for container to start..."
sleep 5

# Bash into the container and run the game
echo "Bashing into container and starting game..."
docker exec -it $CONTAINER_NAME bash -c "java GameLoop"