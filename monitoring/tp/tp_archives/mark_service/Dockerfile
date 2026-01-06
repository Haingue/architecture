FROM maven:3.9.9-amazoncorretto-21 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn install -DskipTests

FROM openjdk:21-slim
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/target/mark-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["sh", "-c", "java -jar /app/mark-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev"]
