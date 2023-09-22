# Product Service

## Swagger documentation
    http://localhost:<port>/swagger-ui/index.html

## Endpoints
- Random integer: return random number.
    - url: /random
    - parameters:
        - <optional\> min
        - <optional\> max
    - metrics:
        - Number of error
        - Latency (ms)

## Java
```bash
$ mvn install
$ java -jar target/monitoring-0.0.1-SNAPSHOT.jar
```

## Docker
Build image
```bash
$ docker build -t product_service .
$ docker run -p <host port>:8080 --name <container name> -d product_service
```

Use public image
```bash
$ docker run -p <host port>:8080 --name <container name> -d haingue/product_service
```