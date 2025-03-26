# Use an official Java runtime as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file of your microservice into the container
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your microservice listens on
EXPOSE 8765

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]