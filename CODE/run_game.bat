@echo off
set IMAGE_NAME=java-game
set CONTAINER_NAME=java-game-container

echo Building Docker image...
docker build -t %IMAGE_NAME% .

echo Checking for existing container...
docker ps -q -f name=%CONTAINER_NAME% >nul
if not errorlevel 1 (
    echo Stopping and removing existing container...
    docker stop %CONTAINER_NAME%
    docker rm %CONTAINER_NAME%
)

echo Ensuring network exists...
docker network ls | findstr /C:"game_network" >nul
if errorlevel 1 docker network create game_network

echo Starting container...
docker run -it --name %CONTAINER_NAME% --network=game_network %IMAGE_NAME%

pause
