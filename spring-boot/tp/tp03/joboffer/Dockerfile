# Build application
FROM maven:3.9.11-amazoncorretto-21-debian as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean install # -Dspring-boot.run.profiles=production
## RUN mvn clean package -Dspring-boot.run.profiles=production

# Run application
FROM openjdk:21-slim
WORKDIR /opt/app
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring
EXPOSE 8080
COPY --from=builder /app/target/*.jar /opt/app
ARG SECURITY_USER=admin
ENV SECURITY_USER=$SECURITY_USER
ARG SECURITY_PASSWORD=changeit
ENV SECURITY_PASSWORD=$SECURITY_PASSWORD
ARG DB_DRIVER=org.h2.Driver
ENV DB_DRIVER=$DB_DRIVER
ARG DB_DIALECT=org.hibernate.dialect.H2Dialect
ENV DB_DIALECT=$DB_DIALECT
ARG DB_URL=jdbc:h2:mem:JobOfferService
ENV DB_URL=$DB_URL
ARG DB_USERNAME=sa
ENV DB_USERNAME=$DB_USERNAME
ARG DB_PASSWORD=default
ENV DB_PASSWORD=$DB_PASSWORD
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=production -jar /opt/app/*.jar "]