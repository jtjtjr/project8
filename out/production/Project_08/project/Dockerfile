# Use an official Java image as the base
FROM openjdk:17-jdk-slim

# Set a working directory inside the container
WORKDIR /app

# Copy Java files into the container
COPY . /app

# Expose a port (optional, depends on your Java program)
EXPOSE 8080

RUN javac Frontend.java
RUN javac GameLoop.java


CMD ["java Frontend"]