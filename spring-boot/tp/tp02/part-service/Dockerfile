# Build application
FROM maven:3.8.4-openjdk-17-slim as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean install -Dspring-boot.run.profiles=production
## RUN mvn clean package -Dspring-boot.run.profiles=production

# Run application
FROM openjdk:17-slim
WORKDIR /opt/app
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring
EXPOSE 8080
COPY --from=builder /app/target/*.jar /opt/app
ARG SECURITY_USER_NAME=admin
ENV SECURITY_USER_NAME=$SECURITY_USER_NAME
ARG SECURITY_USER_PASSWORD=changeit
ENV SECURITY_USER_PASSWORD=$SECURITY_USER_PASSWORD
ARG DB_HOST=localhost
ENV DB_HOST=$DB_HOST
ARG DB_NAME=PartService
ENV DB_NAME=$DB_NAME
ARG DB_USERNAME=postgresql
ENV DB_USERNAME=$DB_USER
ARG DB_PASSWORD=
ENV DB_PASSWORD=$DB_PASSWORD
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=production -jar /opt/app/*.jar "]