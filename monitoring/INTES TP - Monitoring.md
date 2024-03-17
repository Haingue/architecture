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

---

## Prérequis

Pour démarrer, vous devez avoir un composant à monitorer.
Pour cela, 2 options:

- Utiliser l'API Product Service example ([docker hub](https://hub.docker.com/r/haingue/product_service))
- Utiliser votre propre application

### Option 1: Lancer une application web

Vous pouvez utiliser l'image docker **haingue/product_service** pour démarrer une simple API Web codé avec Spring Boot.

```yml
version: '3.7'
services:
  product_service:
    image: haingue/product_service
    ports:
      - 8085:8080
    restart: always
```

Rendez-vous sur `http://localhost:8085/swagger-ui/index.html` pour tester l'API

### Option 2: Adaptez et utilisez vos applications

#### Spring-Boot

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

#### Nestjs

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

---

## Mettez en place votre monitoring

<center>

![](../resources/images/monitoring-architecture.png)

</center>

---

## Prometheus

Pour commencer, le composant le plus important de votre architecture est l'outils qui collectera et centralisera vos mesures.

Pour cela, nous allons utiliser [Prometheus](https://prometheus.io/docs/introduction/overview/) !

[Prometheus tutoriel](https://prometheus.io/docs/prometheus/latest/getting_started/)

[Prometheus documentation](https://prometheus.io/docs/prometheus/latest/configuration/configuration/)

### Instanciez Prometheus

D'après la [documentation officiel de Prometheus](https://prometheus.io/docs/prometheus/latest/installation/), vous pouvez utiliser un conteneur pour créer votre première instance.

<details>
<summary>Cliquez pour voir une aide</summary>

```yml
prometheus:
image: prom/prometheus:v2.36.2
ports:
  - 9090:9090
```

</details>

### Configurez Prometheus

Vous devez configurer Prometheus pour monitorer votre application et ajuster certains comportement.

<details>
<summary>Cliquez pour voir une aide</summary>

Il faut créer un fichier [prometheus.yml](prometheus/conf/prometheus.yml) et le partager avec le conteneur.
Dans ce fichier, il y aura toutes les propriétés de Prometheus dont les cybles à monitorer.

</details>

### Testez Prometheus

Rendez-vous sur l'interface de Prometheus et essayez de visualiser certaines mesures de votre applications.

<details>
<summary>Cliquez pour voir une aide</summary>
Pour afficher le nombre de requête avec un code 2xx par server:
sum(http_server_requests_seconds_count{status=~"2.."}) by (instance)
</details>

### Bonus

- Rendez votre conteneur persistent

---

## Grafana

Maintenant que vous collectez des mesures, il vous faut les visualiser.
Pour cela nous allons utiliser [Grafana](https://grafana.com/) !

[Grafana documentation](https://grafana.com/docs/grafana/latest/setup-grafana/installation/docker/)

### Instanciez Grafana

D'après la [documentation officiel de Grafana](https://grafana.com/docs/grafana/latest/setup-grafana/installation/), vous pouvez utiliser un conteneur pour créer votre première instance.

<details>
<summary>Cliquez pour voir une aide</summary>

```yml
grafana:
  image: grafana/grafana
  depends_on:
    - prometheus
  ports:
    - 3000:3000
```

</details>

### Créez votre premier dashboard

### Importez un dashboard détaillé

### Exportez votre dashboard

### Configurer Grafana

Si vous voulez configurer automatique Grafana, vous pouvez donner au conteneurs des fichiers YAML pour préremplir l'application (datasource, dashboard, ...)

Pour cele, il vous suffit de remplacer les fichiers ce trouvant dans le dossier _/etc/grafana/provisioning/_ du conteneur.

---

## AlertManager

A ce stade, vous monitorez vos application mais n'être pas alarmé en cas de problème.
Pour cela nous allons utiliser [AlertManager](https://github.com/prometheus/alertmanager) !

[AlertManager documentation](https://prometheus.io/docs/alerting/latest/configuration/)

### Instanciez AlertManager

### Configurez AlertManager

### Configurez Prometheus

### Testez AlertManager

<details>
<summary>Cliquez pour voir une aide complète</summary>

```shell
docker-compose up -d
```

</details>

---

## Déployez vos applications en même temps que votre monitoring

### Ajoutez Prometheus/Grafana/AlertManager à vos docker-compose
