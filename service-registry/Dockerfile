FROM openjdk:17-jdk-slim

COPY /target/*.jar app.jar

EXPOSE 8761

CMD ["java", "-jar", "app.jar"]