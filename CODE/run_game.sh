#!/bin/bash

IMAGE_NAME="java-game"
CONTAINER_NAME="java-game-container"

echo "Checking if Docker Desktop is running..."
if ! pgrep -f "Docker Desktop" > /dev/null; then
    echo "Starting Docker Desktop..."
    open -a Docker
    
    echo "Waiting for Docker to initialize..."
    sleep 10
    
    # Wait until Docker is ready
    until docker info > /dev/null 2>&1; do
        echo "Docker is still starting, waiting..."
        sleep 5
    done
fi

echo "Building Docker image..."
docker build -t "$IMAGE_NAME" .

echo "Checking for existing container..."
if [ -n "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "Stopping and removing existing container..."
    docker stop "$CONTAINER_NAME"
    docker rm "$CONTAINER_NAME"
fi

echo "Ensuring network exists..."
if ! docker network ls | grep -q "game_network"; then
    docker network create game_network
fi

echo "Starting container..."
docker run -it --name "$CONTAINER_NAME" --network=game_network "$IMAGE_NAME"