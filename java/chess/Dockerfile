ARG JAR_FILE=*.jar
ARG ENV_NAME=default

# Build application
FROM maven:3.8.4-openjdk-21-slim as builder
ARG ENV_NAME
ENV ENV_NAME $ENV_NAME
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY ./src ./src
COPY ./config ./config
RUN mvn clean install -P$ENV_NAME
#RUN mvn clean package -Dmaven.test.skip=true -P$ENV_NAME

# Run application
FROM openjdk:21-jre-buster
ARG JAR_FILE
ENV JAR_FILE $JAR_FILE
ARG ENV_NAME
ENV ENV_NAME $ENV_NAME
WORKDIR /app
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring
EXPOSE 8080
COPY --from=builder /app/target/${JAR_FILE} /app
ENTRYPOINT ["sh", "-c", "java -jar /app/$JAR_FILE --spring.profiles.active=$ENV_NAME"]