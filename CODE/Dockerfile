# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy your Java source files into the container
COPY Frontend.java GameLoop.java Player.java Event.java Planet.java ./

# Compile the Java files
RUN javac Frontend.java GameLoop.java Player.java Event.java Planet.java

# Run the game
CMD ["java", "GameLoop"]