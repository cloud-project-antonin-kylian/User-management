FROM maven:3.8-openjdk-17-slim
LABEL authors="AntoninLmp"

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Build JAR, we can add -DskipTests to ignore test
RUN mvn clean install


EXPOSE 8080

# Command to run the service
ENTRYPOINT ["mvn", "test"]
#ENTRYPOINT ["/bin/bash"]