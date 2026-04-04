# Build stage
FROM eclipse-temurin:23-jdk AS build
WORKDIR /app

COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw -q -DskipTests dependency:go-offline

COPY src src
RUN ./mvnw -q -DskipTests package

# Runtime stage
FROM eclipse-temurin:23-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Usamos directamente el comando java. 
# Spring leerá automáticamente PORT y MONGODB_URI del entorno de Render.
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]