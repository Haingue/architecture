FROM maven:3-openjdk-18-slim as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn install -DskipTests

FROM openjdk:19-slim
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/target/monitoring-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["sh", "-c", "java -jar /app/monitoring-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev"]
