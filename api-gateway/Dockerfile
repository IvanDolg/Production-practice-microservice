# Используйте базовый образ Java 17
FROM openjdk:17-jdk-slim

# Скопируйте JAR-файл в контейнер
COPY /target/*.jar app.jar

EXPOSE 9191

# Запустите приложение
CMD ["java", "-jar", "app.jar"]