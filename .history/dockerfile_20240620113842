# Build stage
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

# Copy the source code and pom.xml to the payableAccountiner
COPY . .

# Execute the Maven build
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

# Copy the generated JAR from the build stage to the final image
COPY --from=builder /app/target/*.jar /opt/app/application.jar

# Add a group and a user to run the application
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Set the default command to run the application
CMD ["java", "-jar", "/opt/app/application.jar"]
