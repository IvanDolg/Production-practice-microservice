# Используйте базовый образ Java 17
FROM openjdk:17-jdk-slim

# Скопируйте JAR-файл в контейнер
COPY /target/*.jar app.jar

# Запустите приложение
CMD ["java", "-jar", "app.jar"]