FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app/backend
COPY backend/pom.xml .
RUN mvn dependency:go-offline

COPY backend/src ./src
RUN mvn clean package -DskipTests

FROM node:18-alpine AS frontend-builder
WORKDIR /app/frontend
COPY frontend/package*.json .
RUN npm install

COPY frontend . 
RUN npm run build

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/backend/target/*.jar app.jar
COPY --from=frontend-builder /app/frontend/dist ./static

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
