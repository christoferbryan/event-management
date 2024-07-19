FROM maven:3.9-sapmachine-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn dependency:go-offline -B
RUN mvn package -DskipTests

FROM openjdk:21-slim
WORKDIR /app
COPY --from=build ./app/target/*.jar app.jar
EXPOSE ${APP_PORT}
ENTRYPOINT ["java","-jar","/app/app.jar"]