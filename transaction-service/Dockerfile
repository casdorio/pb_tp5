FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/venda-service-0.0.1-SNAPSHOT.jar venda-service.jar

ENTRYPOINT ["java", "-jar", "venda-service.jar"]