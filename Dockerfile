# Build stage
FROM gradle:8.6-jdk17 AS build

WORKDIR /app

COPY . .

RUN gradle clean build -x test

# Run stage
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
