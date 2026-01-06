# Grafana

## Startup
```bash
$ docker-compose up -d
```

## Configuration
### Server properties
- config.monitoring
    ```
    GF_SECURITY_ADMIN_PASSWORD=foobar
    ```

### Datasources
- provisioning/datasources/datasource.yml
    ```yml
    - name: <datasource name>
    type: <type>
    access: proxy
    orgId: 1
    url: <data collector>
    password:
    user:
    database:
    basicAuth: false
    basicAuthUser:
    basicAuthPassword:
    withCredentials:
    isDefault: true
    jsonData:
        graphiteVersion: "1.1"
        tlsAuth: false
        tlsAuthWithCACert: false
    secureJsonData:
        tlsCACert: "..."
        tlsClientCert: "..."
        tlsClientKey: "..."
    version: 1
    editable: true
    ```

## Dashboard
All dashboard are store in: ```/provisioning/dashboards/```

Original dashboard ID: **4701**

## Documentation
[Get started](https://grafana.com/docs/grafana/latest/getting-started/)