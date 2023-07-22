ARG JAR_FILE=*.jar
ARG ENV_NAME=default

# Build application
FROM maven:3.8.4-openjdk-17-slim as builder
ARG ENV_NAME
ENV ENV_NAME $ENV_NAME
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean install -Dspring-boot.run.profiles=$ENV_NAME
#RUN mvn clean package -Dspring-boot.run.profiles=$ENV_NAME

# Run application
FROM openjdk:17-slim
ARG JAR_FILE
ENV JAR_FILE $JAR_FILE
ARG ENV_NAME
ENV ENV_NAME $ENV_NAME
WORKDIR /opt/app
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring
EXPOSE 8080
EXPOSE 8085
COPY --from=builder /app/target/${JAR_FILE} /opt/app
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=$ENV_NAME -jar /opt/app/$JAR_FILE "]