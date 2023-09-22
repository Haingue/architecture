# Prometheus

## Startup
```bash
$ docker-compose up -d
```

## Configuration
- Server properties
    ```yml
    scrape_configs:
    - job_name: <job name>
        metrics_path: <url>
        scrape_interval: 5s
        static_configs:
        - targets: [<host>:<port>]
    ```
    ```yml
    alerting:
    alertmanagers:
    - scheme: http
        static_configs:
        - targets:
        - <host>:<port>
    ```

## Documentation
[Get started](https://prometheus.io/docs/introduction/overview/)