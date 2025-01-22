FROM openjdk:17-jdk-alpine
MAINTAINER com.qre
COPY qr_val/qr_val_api/target/qr_val_api-1.0-SNAPSHOT.jar qr_val.jar
ENTRYPOINT ["java", "-jar", "/qr_val.jar"]
EXPOSE 8080
