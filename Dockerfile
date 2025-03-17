# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory
WORKDIR /app

# Copy the entire project (source code and pom.xml)
COPY . /app

# Build the project
RUN mvn clean package -DskipTests

# Expose the port your app runs on
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "target/milker-0.0.1-SNAPSHOT.jar"]