# Sujet TP – Monitoring
Dans ce TP, vous aller apprendre à utiliser déployer un monitoring.

<br><br>
## Objectif
Monitorez vos application NestJs et Spring-Boot.
- Lancez le docker-compose.yml pour tester
    - Plusieurs conteneurs seront lancés
        1. une application Spring-Boot pour produire des métriques.
        2. une instance de Prometheus pour cellecter les metriques.
        3. une instance de Grafana pour visualiser les métriques.
        4. une instance de Alert Manager pour envoyer des alertes.

- Monitorez vos application
    - Adaptez vos applications pour **exposer des métriques**
    - Adaptez la configuration de Prometheus pour **collecter vos les métriques**
    - Ecrivez un docker-compose permettant de lancer Prometheus/Grafana/AlertManager et vos applications
    - Créer un dashboard global et un technique par application

## Tester Prometheus et Grafana
```shell
docker-compose up -d
```

## Adaptez vos applications
### Spring-Boot
- Ajoutez Prometheus à vos dépendance pour générer automatiquement des métriques
    ```xml
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-registry-prometheus</artifactId>
			<scope>runtime</scope>
		</dependency>
    ```
- Ajouter une métrique static pour mieux identifier votre application
    ```properties
    # Metrics parameters (actuator)
    management.endpoints.web.exposure.include=*
    management.endpoint.health.show-details=when_authorized

    # Prometheus tags
    management.metrics.tags.application=<Nom de l'application>
    ```

[Baeldung documentation](https://www.baeldung.com/spring-boot-self-hosted-monitoring)

[Medium tutoriel (custom metrics)](https://mehmetozkaya.medium.com/monitor-spring-boot-custom-metrics-with-micrometer-and-prometheus-using-docker-62798123c714)

### Nestjs

- Installation de nestjs-prometheus
    ```shell
    npm install @willsoto/nestjs-prometheus prom-client
    ```
- Configurer Nestjs
    <center>
        app.module.ts
    </center>

    ```TypeScript
    import { PrometheusModule } from '@willsoto/nestjs-prometheus';
    
    Module({
        imports: [
            ...
            PrometheusModule.register(),
            ...
        ]
    })
    export class AppModule {}
    ```


[Github](https://github.com/willsoto/nestjs-prometheus)

[Medium tutoriel](https://medium.com/cp-massive-programming/how-to-monitor-a-distributed-system-with-a-nestjs-application-edb86d170d4e)

## Déployez vos applications en même temps que votre monitoring

### Ajoutez Prometheus/Grafana/AlertManager à vos docker-compose

### Configuration de prometheus
Modifiez le fichier [prometheus.yml](prometheus/conf/prometheus.yml) pour y ajouter vos applications.

[Prometheus tutoriel](https://prometheus.io/docs/prometheus/latest/getting_started/)

[Prometheus documentation](https://prometheus.io/docs/prometheus/latest/configuration/configuration/)

### Configuration d'AlertManager
[Github AlertManager](https://github.com/prometheus/alertmanager)
[AlertManager documentation](https://prometheus.io/docs/alerting/latest/configuration/)

### Configuration de Grafana
Si vous voulez configurer automatique Grafana, vous pouvez donner au conteneurs des fichiers YAML pour préremplir l'application (datasource, dashboard, ...)

Pour cele, il vous suffit de remplacer les fichiers ce trouvant dans le dossier */etc/grafana/provisioning/* du conteneur.

[Grafana documentation](https://grafana.com/docs/grafana/latest/setup-grafana/installation/docker/)
