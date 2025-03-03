# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Java source files
COPY Frontend.java GameLoop.java mysql-connector-java-8.0.xx.jar ./

# Compile Java files
RUN javac -cp .:mysql-connector-java-8.0.xx.jar Frontend.java GameLoop.java

# Run game
CMD ["java", "-cp", ".:mysql-connector-java-8.0.xx.jar", "GameLoop"]