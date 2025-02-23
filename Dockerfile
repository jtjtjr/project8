# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy your Java source files into the container
COPY Frontend.java GameLoop.java ./

# Compile the Java files
RUN javac Frontend.java GameLoop.java

# Keep the container running with an interactive shell
CMD ["sh", "-c", "echo 'Container is running. Use docker exec -it <container_name> bash to play the game.' && tail -f /dev/null"]