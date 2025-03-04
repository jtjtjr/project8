# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy your Java source files into the container
COPY Event.java Player.java Frontend.java GameLoop.java ./

# Compile the Java files
RUN javac Event.java Player.java Frontend.java GameLoop.java


# Run game
CMD ["java", "-cp", ".:mysql-connector-java-8.0.xx.jar", "GameLoop"]