# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy your Java source files into the container
COPY Frontend.java GameLoop.java Player.java Event.java Planet.java PlanetLoader.java EventSQL.java EventConnector.java frontendUXElements.java./

# Copy the planets.csv from the parent directory
COPY ../planets.csv ./ 

# Compile the Java files
RUN javac Frontend.java GameLoop.java Player.java Event.java Planet.java PlanetLoader.java EventSQL.java EventConnector.java frontendUXElements.java

# Run the game
CMD ["java", "GameLoop"]