FROM openjdk:17-jdk-slim
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY init.sql .
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/milker-0.0.1-SNAPSHOT.jar"]