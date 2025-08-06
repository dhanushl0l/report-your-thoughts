# Build stage with Java 24 support
FROM gradle:8.14-jdk-21-and-24-corretto-al2023 AS build

WORKDIR /app
COPY . .

RUN ./gradlew clean build -x test

# Runtime stage
FROM openjdk:24-jdk-slim

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
