# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy your Java source files into the container
COPY *.java ./

# Create a directory for dependencies
RUN mkdir -p lib
COPY mysql-connector-j-9.2.0/mysql-connector-j-9.2.0.jar ./lib/

# Copy the planets.csv and lore.csv from the parent directory
COPY ../planets.csv ../lore.csv ../shopItems.csv ../events.csv ./ 

# Compile the Java files
RUN javac -cp lib/mysql-connector-j-9.2.0.jar Frontend.java GameLoop.java Player.java Event.java Planet.java PlanetLoader.java EventSQL.java EventConnector.java frontendUXElements.java LoreLoader.java Shop.java Tutorial.java ShipDisplayer.java CompanyStore.java ShopItem.java ShopItemLoader.java MysteryEvent.java CrewMates.java EndGame.java EventCSV.java Sage.java

# Run the game
CMD ["java",  "-cp", ".:lib/mysql-connector-j-9.2.0.jar","GameLoop"]