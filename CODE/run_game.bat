@echo off 

echo Open Docker before other tasks


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

echo Starting container...
start /b docker run -d --name %CONTAINER_NAME% %IMAGE_NAME%

echo Waiting for container to start...
timeout /t 5 >nul

echo Bashing into container and starting game...
docker exec -it %CONTAINER_NAME% bash -c "java GameLoop"
pause