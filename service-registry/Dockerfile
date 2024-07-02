# Используем официальный образ OpenJDK 17
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл jar в контейнер
COPY target/service-registry-0.0.1-SNAPSHOT.jar /app/my-microservice.jar

# Указываем команду для запуска приложения
CMD ["java", "-jar", "/app/my-microservice.jar"]