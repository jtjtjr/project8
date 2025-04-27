#!/bin/bash

echo "Running game . . ."

# Compile Java files with UTF-8 encoding and proper classpath
javac -encoding UTF-8 -cp "lib/mysql-connector-j-9.2.0.jar" \
Frontend.java GameLoop.java Player.java Event.java Planet.java PlanetLoader.java \
EventSQL.java EventConnector.java frontendUXElements.java LoreLoader.java Shop.java \
Tutorial.java ShipDisplayer.java CompanyStore.java ShopItem.java ShopItemLoader.java \
MysteryEvent.java CrewMates.java EndGame.java EventCSV.java Sage.java

# Run the game with correct classpath for Unix (colon-separated)
java -cp ".:lib/mysql-connector-j-9.2.0.jar" GameLoop
