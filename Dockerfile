FROM maven:3.8-openjdk-17-slim AS build
LABEL authors="AntoninLmp"
WORKDIR /app

# Copy the pom and dependency only when they change
COPY pom.xml .
RUN mvn dependency:go-offline 

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-slim 
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/user-management-1.0-SNAPSHOT.jar .

RUN groupadd -r userService && useradd -r -g userService userService
USER userService

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "user-management-1.0-SNAPSHOT.jar"]