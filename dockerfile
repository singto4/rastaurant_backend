FROM openjdk:8-jdk-alpine

RUN mkdir -p /app

COPY target/RestaurantProject-1.0.0-SNAPSHOT.jar /app

CMD ["java", "-jar", "-Xms2048m", "-Xmx2048m", "/app/RestaurantProject-1.0.0-SNAPSHOT.jar"]

EXPOSE 9090