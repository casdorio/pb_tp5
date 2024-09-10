FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/vatrate-0.0.1-SNAPSHOT.jar vatrate.jar

ENTRYPOINT ["java", "-jar", "vatrate.jar"]