# Etapa de build
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiamos el jar generado desde el builder
COPY --from=builder /app/target/*.jar app.jar

# Exponemos el puerto de Spring Boot
EXPOSE 8080

# Ejecutamos la app
ENTRYPOINT ["java","-jar","app.jar"]
