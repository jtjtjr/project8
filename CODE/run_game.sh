#!/bin/bash

IMAGE_NAME="java-game"
CONTAINER_NAME="java-game-container"

echo "Building Docker image..."
docker build -t "$IMAGE_NAME" .

echo "Checking for existing container..."
docker ps -q -f name="$CONTAINER_NAME" > /dev/null
if [ $? -eq 0 ]; then
    echo "Stopping and removing existing container..."
    docker stop "$CONTAINER_NAME"
    docker rm "$CONTAINER_NAME"
fi

echo "Ensuring network exists..."
docker network ls | grep -q "game_network"
if [ $? -ne 0 ]; then
    docker network create game_network
fi

echo "Starting container..."
docker run -it --name "$CONTAINER_NAME" --network=game_network "$IMAGE_NAME"
