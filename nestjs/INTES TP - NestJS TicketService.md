# Sujet TP – NestJs
Dans ce TP, vous aller apprendre à utiliser NestJs et ces modules pour créer une application web.

<br/>

## Objectif
Créer une application NestJs permettant de gérer l'achat de ticket.
-	Acheter un ticket
-	Revendre un ticket
-	Obtenir les informations d'un ticket
-	Obtenir la liste des tickets pour un event

<center>

```plantuml
@startuml
archimate #Business "Gestion des tickets" as ticket_service <<business-process>>
archimate #Business "Retrieve ticket details" as ticket_details <<business-function>>
archimate #Business "search all event tickets" as ticket_list <<business-function>>
archimate #Business "Buy event tickets" as buy_ticket <<business-function>>
archimate #Business "Sell event ticket" as sell_ticket <<business-function>>

ticket_service --> ticket_details
ticket_service --> ticket_list
ticket_service --> buy_ticket
ticket_service --> sell_ticket
@enduml
```

</center>

<br/>

## Prérequis
-	[Node.js installé sur votre machine](https://nodejs.org/fr)
    -	Version 18.16.1 conseillée
-	IDE configuré
    -	[VS Code](https://code.visualstudio.com/)
    -	[WebStorm](https://www.jetbrains.com/fr-fr/lp/toolbox/ "Outil pour installer la suite JetBrain") (student edition)
- [Docker installé sur votre machine](https://docs.docker.com/engine/install/ "Documentation officielle")

<br/>

## 1. Initialisation du projet
Pour initialiser votre projet NestJs, vous aller installer puis utiliser l’outil **nestjs-cli** :
```shell
$ npm i -g @nestjs/cli
$ nest new ticket-service
```

-	Package manager: **npm**

<br/>

Installez les packets suivants:
-	Jest
-	Handlebars
-	TypeORM
-	Postgresql Driver
```shell
$ npm i --save-dev @nestjs/testing
$ npm install --save hbs @nestjs/typeorm typeorm pg
```

<br/>

Lancez votre application :
```shell
$ npm install
$ cd ticket-service
$ npm run start
```
![Log de votre console](../resources/images/nestjs-started-log.png)

Rendez-vous sur [http://localhost:3000](http://localhost:3000 "Lien vers votre application locale") et vous devriez avoir cet écran : ***Hello World !***

Bravo votre installation fonctionne !

<br/>

## 2. Hello World
Créez votre premier contrôleur.
![Log de votre console](../resources/images/nestjs-hello-world.png)

<br/>

Placez le fichier **hello.controller.spec.ts** dans le même dossier que votre nouveau contrôleur
Et lancez la commande suivante :
```shell
$ npm run test
```
<details>
<summary>Cliquez pour voir le contenu de ce fichier.</summary>

```TypeScript
import { BadRequestException } from '@nestjs/common';
import { Test } from '@nestjs/testing';
import { HelloController } from './hello.controller';
import { HelloService } from './hello.service';

describe('HelloController', () => {
  let helloController: HelloController;

  beforeEach(async () => {
    const moduleRef = await Test.createTestingModule({
      controllers: [HelloController],
      providers: [HelloService]
    }).compile();

    helloController = moduleRef.get<HelloController>(HelloController);
  });

  describe('hello', () => {
    it('should return "Hello World!"', () => {
      expect(helloController.getHello()).toBe('Hello World !');
    });
  });
  
  describe('hello name', () => {
    it('should return "Hello World!" for a specific name', () => {
      expect(helloController.getHelloByName('test')).toBe('Hello World test !');
      try {
          helloController.getHelloByName(null)
      } catch (error) {
        expect(error).toBeInstanceOf(BadRequestException)
      }
    });
  });
});
```
> Ce fichier contient le test qui vérifiera si votre contrôleur fonctionne, n’hésitez pas à y jeter un coup d’œil pour le comprendre.
</details>

<br/>

## 3. HTML dynamique
Maintenant que vous avez compris le fonctionnement d’un contrôleur, vous allez pouvoir utiliser un moteur de template pour générer une page HTML dynamique.

<br/>

Nous allons utiliser **HandleBars**, mais vous pouvez parfaitement rechercher une autre solution couramment utilisé avec NestJs.

Installation de HandleBars
```shell
$ npm install --save hbs
```
Documentation de Handlebars : [docs.nestjs.com/techniques/mvc](https://docs.nestjs.com/techniques/mvc "Documentation officielle de HandleBar")

<br/>

Pour utiliser des ressources statiques comme des fichiers JS/CSS pour votre page HTML, il vous faut ajouter et paramétrer la library **serve-static** de NestJs :
```shell
$ npm install --save @nestjs/serve-static
```

Documentation : [docs.nestjs.com/recipes/serve-static](https://docs.nestjs.com/recipes/serve-static "Documentation officielle de Serve-Static")

<br/>

## 4. Les Entités
Il est temps de voir comment utiliser une base de données.

<br/>

Dans un premier, vous pouvez dès maintenant créer une base de données en utilisant le fichier intes_db.yml pour créer un container avec une base **Postgresql** :
```shell
$ docker-compose -f ./docker-compose.ticketservice.yml up -d
```

<br/>

Dans l’interface de **Docker Desktop**, vous devriez avoir un groupe de 3 containers :
-	ticket-service-db : base de données Postgresql pour l’application TicketService.
    - Host : ticket-service-db
    - Port : 8096
    - User : TicketService_USER
    - Password : jaimelacomplexitedespassword
    - Database name : TicketService

<br/>

Ensuite, vous pouvez utiliser **TypeORM** pour manipuler vos données depuis votre application NestJs.
```shell
$ npm install --save @nestjs/typeorm typeorm pg
```

Documentation : [docs.nestjs.com/techniques/database](https://docs.nestjs.com/techniques/database#typeorm-integration "Documentation officielle de NestJs pour TypeORM")

<br/>

Maintenant, votre application est prête pour créer vos entités et les méthodes pour les gérer.

**Entité** :

```plantuml
@startuml
Class Ticket {
  __id__: UUID
  event: Event
  participant: UUID
  paymentDatetime: LocalDatetime
  creationDatetime: LocalDatetime
}
Class Event {
  __id__: UUID
  title: String
  ticketPrice: double
  datetime: LocalDatetime
}

Ticket "event" *-- Event 

@enduml
```

<br/>

**Méthodes métiers**
-	TicketDto buyTicket (TicketDto entity)
-	TicketDto sellTicket (TicketDto entity)
-	TicketDto findOneTicket (UUID id)
-	List\<TicketDto> findAllTicketForOneEvnt (UUID eventId)

<br/>

## 5. Les Guards
Avant d’aller plus, il serait judicieux de protéger votre application.

Pour cela nous pouvons utiliser les **Guards**, ce type d’objet vous nous permettre de définir des méthodes d’autorisation de requêtes http, dans le but de vérifier si le demandeur a le droit d’accéder à la ressource qu’il a demandé.

<br/>

Pour commencer, vous pouvez utiliser des tests simples pour autoriser les requêtes.

<br/>

## 6. Gérer vos entités
Comme votre application est sécurisé, vous pouvez créer des points de terminaisons (**endpoints**) pour manipuler vos entités.

<br/>

**Contrat de service**

| Method | url | request body| response body | Result | Description |
| --- | --- | --- | --- | --- | --- |
| GET | /ticket/{id} | | {}:Ticket | 200 ou 404 | Récupére un tickets |
| GET | /ticket?eventId={eventId} | | [ ]: Collection\<Ticket> | 200 ou 204 | Lister tous les tickets d'un event |
| POST | /buy-ticket | Ticket | Ticket | 200 ou 204 | Acheter un ticket |
| POST | /sell-ticket/{id} | | Ticket | 200 ou 400 | Revendre un ticket |

Vous pouvez également ajouter un tableau HTML sur votre page d’accueil pour être capable de voir le contenu de votre base de données.