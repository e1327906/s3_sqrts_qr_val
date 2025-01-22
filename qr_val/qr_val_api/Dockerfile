# Use an OpenJDK 17 Alpine image as the base image
FROM openjdk:17-jdk-alpine
# Copy the JAR file from the host to the Docker image
COPY target/qr_val_api-1.0-SNAPSHOT.jar qr_val.jar
# Specify the command to run the JAR file
ENTRYPOINT ["java", "-jar", "/qr_val.jar"]
EXPOSE 8090

