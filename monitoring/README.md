# IMT - Monitoring
Quick demo of monitoring.
<br>

## Steps
1. Install docker desktop.
2. Launch Prometheus, Grafana, Product_service and try them.
    ```bash
    $ docker-compose up -d
    ```
3. Create new instance of Product_service on other port.
4. Configure Prometheus to collect metrics from the second Product_service instance.
5. Visualize collected data with Grafana.
6. Create simpler dashboard.
7. **Bonus**: create your own web service and expose your custom metrics

<br>

## Dossier
- product_service: application pour exposer un service et des metrics (API)
- prometheus: system permettant de collecter des metrics et de les historiser
- grafana: system permettant d'afficher des donées
