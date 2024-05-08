
#This is for render deploy
# Build stage

FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/Database-1.0.jar database.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "database.jar"]
