# Store A
Projet Spring Boot pour gérer l'entrepôt A.

## Role et Utilisateurs

| Role         | Lectuer des données | Gestion d'item | Création de commande | Gestion des utilisateurs | Gestion du système |
|:-------------|:-------------------:|:--------------:|:--------------------:|:------------------------:| :---: |
| Anonymous    |          X          |                |                      |                          | 
| ROLE_USER    |          X          |       X        |          X           |                          |
| ROLE_MANAGER |          X          |       X        |          X           |            X             |
| ROLE_ADMIN   |          X          |       X        |          X           |                          |X

## Démarrage
### Créer l'archive exécutable

```shell
mvn clean install
```

### Lancer les tests unitaires

```shell
mvn test
```


### Lancer l'application en mode développement

```shell
mvn clean spring-boot:run -Dspring-boot.run.profiles=dev -Dspring-boot.run.fork=false
```

> Pour pouvoir attacher un debugger pendant l'exécution de votre application, vous devez ajouter les arguments suivants:
> ```txt
> -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8001
> ```
