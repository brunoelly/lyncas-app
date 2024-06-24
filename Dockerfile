FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

COPY --from=builder /app/target/*.jar /opt/app/application.jar

ENTRYPOINT ["java", "-jar", "/opt/app/application.jar"]
