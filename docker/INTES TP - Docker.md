# Sujet TP - Docker
Dans ce TP, vous aller apprendre/revoir comment utiliser Docker et déployer une application web.

<br><br>
## Objectif
Conteneuriser en utilisant les bonnes pratiques une application
1. Créer un docker-compose pour créer une base de donnée PostgreSQL
2. Créer une image docker de votre application Spring-Boot
3. Ajouter votre image Spring-Boot votre docker-compose
4. Créer une image docker de votre application Nestjs
5. Ajouter votre image Nestjs votre docker-compose
6. Rendre persistant vos bases de données
7. Isoler vos conteneurs
8. Modifiez vos image pour utiliser du multi-staging
9. Publier vos images docker
10. [BONUS] Utilisez des outils existant de la communauté

<br>

## Prérequis
-	[Docker installé sur votre machine](https://docs.docker.com/engine/install/ "Documentation officielle")

<br><br>

## 1. Utilisez une base de données conteneurisée
Vous pouvez vous aidez du fichier « ***dockerfile/docker-compose.db.yml*** » de l’archive « Ressources.rar » disponible sur MyLearningSpace > Intes.

<br><br>

## 2. Créer une image docker de votre application Spring-Boot
A l’aide du cours, conteneurisez votre application Spring-Boot en utilisant directement l’archive **Jar** fabriquée avec maven.
<br>

<details>
<summary>Solution pour fabriquer le Jar</summary>
Générer un Jar exécutable avec maven :

```shell
$ mvn install
```

L’archive se trouvera dans le dossier « target » de votre projet.
</details>
<br>

Une fois votre archive prête, testez là avant de la conteneuriser :
```shell
$ java -jar ./target/<file.jar>
```

Si votre archive fonctionne, vous pouvez créer votre image docker avec un fichier Dockerfile.

<details>
<summary>Solution de Dockerfile</summary>

```Dockerfile
FROM openjdk:17-slim
WORKDIR /opt/app
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring
EXPOSE 8080
COPY /target/*.jar /opt/app
ENTRYPOINT ["sh", "-c", "java -jar /opt/app/*.jar "]
```

</details>

## 3. Ajouter votre image Spring-Boot votre docker-compose
Ajoutez un service a votre précédent docker-compose

<details>
<summary>Solution</summary>

```yml
  part-service:
    depends_on:
      - part-service-db
    image: intes-part-service
    restart: always
    ports:
      - 8090:8080
    networks:
      - intes
    environment:
      SECURITY_USER_NAME: admin
      SECURITY_USER_PASSWORD: changeit
      DB_HOST: part-service-db
      DB_NAME: PartService
      DB_USERNAME: PartService_USER
      DB_PASSWORD: kqe!12lsdjconz34aejw
```
</details>



## 4. Créer une image docker de votre application Nestjs
Nestjs est un framework Nodejs, il vous faut donc utiliser une image de base [Nodejs](https://hub.docker.com/_/node/) et y placer votre code.

> il y a plusieurs pratique permettant d'accèler la création de votre image.
> Notament en important vos dépendance avant votre code source.

Ensuite, vous devez trouver la command système permettant de lancer votre application avec le profile d'exécution que vous souhaitez.

<details>
    <summary>Solution</summary>

    ```Dockerfile
    # Base image
    FROM node:18

    # Create app directory
    WORKDIR /opt/app

    # A wildcard is used to ensure both package.json AND package-lock.json are copied
    COPY package*.json /opt/app

    # Install app dependencies
    RUN npm ci
    RUN npm install -g @nestjs/cli

    # Bundle app source
    COPY public /opt/app/public
    COPY src /opt/app/src
    COPY views /opt/app/views
    COPY test /opt/app/test
    COPY nest-cli.json tsconfig*.json /opt/app/
    COPY .eslintrc.js .prettierrc /opt/app/

    # Creates a "dist" folder with the production build
    RUN npm run build

    # Start the server using the production build
    ENTRYPOINT [ "sh", "-c", "node /opt/app/dist/main.js" ]
    ```
</details>

<br/>
Une fois votre fichier terminé, voud devez fabriquer votre image.

<details>
<summary>Solution</summary>

```shell
docker image build -t supplier-service@latest .
```
</details>

<br/>
Vous pouvez utiliser votre nouvelle image !

<details>
<summary>Solution</summary>

```shell
docker run -p 9090:9090 supplier-service@latest
```
</details>

<br/>

## 5. Ajouter votre image Nestjs votre docker-compose
Ajoutez un service a votre précédent docker-compose

<details>
<summary>Solution</summary>

```yml
  supplier-service:
    depends_on:
      - supplier-service-db
    image: haingue/intes-supplier-service:latest
    restart: always
    ports:
      - 8095:3000
    networks:
      - intes
    environment:
      DB_HOST: supplier-service-db
      DB_PORT: 5432
      DB_USERNAME: SupplierService_USER
      DB_PASSWORD: qsp!10kpuocqs6da2ze?
      DB_NAME: SupplierService
```
</details>

## 6. Rendre persistant vos bases de données
Pour rendre des informations persistentes, vous devez utiliser des volumes.
Chaque image/technologie précise quel dossier est necéssaire pour persister des informations.

Grâce à un volume, vous allez pouvoir faire en sorte de toujours garder vos données même quand votre container est détruit.

> Pensez à lire la documentation !

<details>
<summary>Solution</summary>

```yml
    ...
    volumes:
      - <host folder>:<container folder>
    ...
```
</details>

## 7. Isoler vos conteneurs
Il est parfois préférable d'isoler certain container, dans le cours vous avez vu comment permettre à des containers de communiquer uniquement entre eux.

Utilisez cette méthode pour isoler les base de données de vos applications, elles ne doivent pas être accessible via l'extérieur.

<center>

![Architecture isolant la base de données du reste du réseau tout en laissant accessible l'application Spring Boot](../resources/images/docker-compose-architecture.png)

</center>

<details>
<summary>Solution</summary>
Il suffit d'utiliser un network.

```yml
services:
    service1-db:
        ...
        networks:
            - service1-internal-network
        ...
    service1:
        ...
        networks:
            - service1-internal-network
            - service-network
        ...
    service2:
        ...
        networks:
            - service-network
        ...
networks:
  service1-internal-network:
  service-network:
```
> Dans cet exemple le *service2* peut uniquement communiquer avec le *service1*.<br/>
> Le *service1-db* est isolé du service *service2*

> Il est possible de partager un network entre plusieurs fichier, il suffit de réutiliser le même nom.
</details>

## 8. Modifiez vos image pour utiliser du multi-staging
Dans le cour, vous avez vu comment utiliser le multi-staging.
Cela permet de reduire la surface d'attaque de vos containers.

Sécurisez vos images à l'aide des cours et des ressources internet.

## 9. Bonus : publier votre image docker
Une fois votre image docker prête, vous pouvez la publier sur un registre d’image publique (ex : [DockerHub](https://hub.docker.com/search?q= "hub.docker.com"))

1.	Créer un compte sur Docker Hub (gratuit)
2.	Lancer la commande pour vous authentifier sur votre machine
    ```shell
    $ docker login
    ```

3.	Pousser votre image sur le dépôt d'image<br>
    ```shell
    $ docker push <dépôt>/<nom image>:<tag de l’image>
    ```
    >*(Docker Hub est configurer par défaut, donc pas besoin de le spécifier)*<br>

    Ex:
    ```shell
    $ docker push YourLogin/ImageName:latest
    ```
<br>

Maintenant, n’importe qui peut utiliser votre image docker (y compris vous), en utilisant la commande suivante :
```shell
$ docker pull <dépôt>/<nom image>:<tag de l’image>
```

Ex :
```shell
$ docker pull YourLogin/ImageName:latest
```

## 10. [BONUS] Utilisez des outils existant de la communauté
Il existe de nombreux outils que vous pouvez installer via docker, essayez s'en.

> Sur DockerHub, vous pouvez trouver toutes les images du monde.<br/>
> Sur la page d'une image, il y a toutes les informations necessaire (persistence, paramètres, documentation, ...)

<details>
<summary>Lancer tout les exemples</summary>

```shell
docker compose \
-f docker-compose.tools.yml \
-f docker-compose.partservice.yml \
-f docker-compose.supplierservice.yml \
up -d
```
</details>
