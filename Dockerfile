FROM openjdk:11-jdk-slim

WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/customers-api-0.0.1-SNAPSHOT.jar /app/customers-api-0.0.1-SNAPSHOT.jar

# Expose the port your application runs on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "customers-api-0.0.1-SNAPSHOT.jar"]
