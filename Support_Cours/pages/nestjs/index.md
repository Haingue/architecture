---
theme: seriph
title: Formation NestJs
class: text-center
drawings:
  persist: false
transition: slide-left
mdc: true
seoMeta:
  ogImage: auto
plantUmlServer: https://www.plantuml.com/plantuml
layout: cover
---

<Cover title="Formation NestJs" logoUrl="/images/nestjs-logo.png" />

---
title: Sommaire
layout: chapter_title
---

- Javascript
  - Fonctionnement
  - Synctaxe
- Node.js
  - Principes
  - Fonctionnement
- Nest.js
  - ...
  - Modules

---
title: Javascript
layout: chapter_title
transition: slide-up
---

::bottom::

Javascript est un langage de script créé en 1995 par <b>Sun Microsystems</b> et <b>Netscape</b> pour le navigateur Netscape.
Initialement, il était utilisé pour donner du dynamise aux pages HTML côté <b>navigateur (client)</b>, mais maintenant, on l’utilise aussi bien pour du backend/frontend côté client/serveur.

::top::

<img class="m-auto" scr="/images/nestjs-netscape.jpeg" />

---

> Au départ, le langage devait s'appeler LiveScript, mais le nom Javascript a été utilisé pour faciliter l’acception de la technologie et faire un coup de pub. <br/>
> Au final, cela a causé de la confusion entre Java et JavaScript.

Javascript est un langage qui n’a pas de moteur officiel. C’est-à-dire qu’à l’époque, Netscape avait son moteur Javascript et Microsoft le sien nommé Jscript, etc…

---
title: Javascript
layout: chapter_title
---

## Fonctionnement

Javascript est un langage de type script basé sur la [programmation orientée prototype](https://developer.mozilla.org/fr/docs/Glossary/Prototype-based_programming).<br/>
Il a été standardisé pour facilité son utilisation, c’est ECMA international qui s’en est occupé pour la première fois en 1997

ECMA est un ensemble de norme à propos des langages de type script créés par ECMA international.

---
title: Javascript
layout: chapter_title
---

### Standards
- Standardisé sous le nom <b>ECMAScript</b>
  - ECMA-262: 1997
  - ECMA-6: 2015
  - ECMA-12: 2021
  - ECnext: en cours

  <img class="m-auto" scr="/images/nestjs-ecmascript.png" />

---
title: Javascript
layout: chapter_title
---

### Comment Javascript, fonctionne-t-il ?
C’est un <b>langage interprété</b>.<br>
C’est-à-dire que le code source et directement lu pendant son exécution ce qui rend le langage <b>cross-platform</b>.

<img class="m-auto" src="/images/nestjs-interprete.png" />

---
title: Javascript
layout: chapter_title
---

## Syntaxe

- Variable 
  - var
  - let 
  - const

- Function
  ```js
  function maFonction () {
    return 42;
  }
  ```
---

- Fonction anonyme
  ```js
  () => 42
  ```
  ```js
  () => {
    return 42
  }
  ```

---

- Prototype
  ```js
  function UserConstructeur (name) {
    this.name = name;
    this.age = 20;
  }
  let user1 = new UserConstructeur("Pierre");
  ```
- Object (ECMAScript 6)
  ```js
  class User {
    constructor (name) {
      this.name = name
      this.age = 20;
    }
  }
  let user = new User("Pierre");
  ```

---
title: Javascript
subtile: Syntaxe
layout: chapter_title
---

::left::

### ECMAScript

```js
class User {
    constructor (name) {
        this.name = name
        this.age = 20;
    }

    getName () {
	return this.name
    }
}


let user1 = new User("Pierre")
user1.getName()
```

::right::

### Java

```java
class User {

   String name;
   int age;

   public User(String name, int age) {
      this.name = name;
      this.age = age;
   }

   public String getName() {
      return name;
   }
}

User user1 = new User("Pierre");
user1.getName();
```

---
title: Node.js
layout: chapter_title
---

## Node.js

<img class="m-auto" scr="/images/nestjs-nodejs-logo.png" />

Node.js est un moteur d'exécution de Javascript qui fonctionne côté serveur, il utilise :
- le moteur Javascript <b>V8</b> de Google
- la librairie <b>LibUV</b> (permet de gérer sa boucle événementielle).

---

Ce projet open-source permet de créer des applications web événementiel:
- <b>cross-platforme</b>
- <b>hautement concurrent</b> (ne créer pas de nouveau thread par requête d'utilisateur et est non bloquant).

---
title: Node.js
layout: chapter_title
---

## Boucle d'événement

<img class="m-auto" scr="/images/nestjs-node-eventloop.png" />

voir: [practicalprogramming.fr/event-loop-nodejs](practicalprogramming.fr/event-loop-nodejs)

---
title: Fonctionnement
layout: chapter_title
---

```js
const { createServer } = require('http');

//Creation du serveur
const server = createServer((request, response) => {
    response.writeHead(200, {'Content-Type': 'text/plain'});
    response.end('Hello World\n');
});

server.listen(3000, () => 
    console.log('Adresse du serveur: http://localhost:3000')
);
```

---
title: Fonctionnement
layout: impacting_message
---

#### Comment Javascript, fonctionne-t-il ?
C’est un <b>langage interprété</b>.<br>
C’est-à-dire que le code source et directement lu pendant son exécution ce qui rend le langage <b>cross-platform</b>.

<img class="m-auto" src="/images/nestjs-interpreter-node.png" />

---
title: Nest.js
layout: chapter_title
---

## Nest.js

<img class="m-auto" scr="/images/nestjs-logo.png" />

Nest.js est un Framework Node.js qui permet de créer des applications côté serveur efficaces et évolutives.<br/>

Comme Spring Boot, il utilise l’<b>inversion de contrôle</b> ([IoC](https://gayerie.dev/docs/spring/spring/principe_ioc.html)), ce qui permet de mieux séparer le code métier du code technique. <br/>

---

On retrouve le même principes:
- <b>Controller</b>: ensemble de classe gérant les entrées/sorties de l’application
- <b>Module</b>: ensemble de classe liant les différentes parties du projet
- <b>Provider</b> (~Service): ensemble de classe gérant les logiques


Avec Nest.js, vous pouvez écrire votre code en Javascript classique (vanilla) ou en TypeScript.
> Il est conseillé d’utiliser TypeScript.<br/>
> TypeScript est une variante du Javascript, « c’est la même chose, mais avec du typage ».

---
title: Nest.js
layout: chapter_title
---

 ## Modules technique

- Web
  - Express
  - fastify
  - WebSockets
- ORM
  - Sequelize
  - TypeORM
  - Mongoose
- Test
  - Jest

[Voir documentation](https://docs.nestjs.com/modules)

---
title: Nest.js
layout: chapter_title
---

 ## TypeScript

Ce langage est un dérivé d'ECMAScript car il ajoute la notion de typage.

```ts
interface User {
  name: string;
  id: number;
}
 
class UserAccount {
  name: string;
  id: number;
 
  constructor(name: string, id: number) {
    this.name = name;
    this.id = id;
  }

  function isAdmin(): boolean {
    //...
  }
}

const user: User = new UserAccount("Murphy", 1);
```

---

```ts
type state = "open" | "close";
let a:state = "open";

let b:state = "ouvert"; // Error: Type '"ouvert"' is not assignable to type 'state'.
```

Tutoriel
- [TypeScript for JavaScript dev](https://www.typescriptlang.org/docs/handbook/typescript-in-5-minutes.html)
- [TypeScript for OOP dev](https://www.typescriptlang.org/docs/handbook/typescript-in-5-minutes-oop.html)

---
title: Nest.js
layout: chapter_title
---

## Controller

Un contrôleur sert à définir les comportements lors de réception de <b>requête HTTP</b> et renvoie une <b>réponse HTTP</b> au client.

---

<div class="m-auto">

**./hello/hello.controller.ts**

</div>

```ts
@Controller({
    path: "/hello"
})
export class HelloController {
    constructor(private readonly helloService: HelloService) {}

    @Get()
    getHello(): string {
      return this.helloService.getHello();
    }
}
```

---

L’annotation <b>@Controller</b> signaler au conteneur Nest IoC que cette classe est un contrôleur, et on peut utiliser certains paramètres pour modifier l’utilisation de cette classe (ex: path).<br/>
Ensuite, j’utilise l’annotation <b>@Get</b> pour définir la méthode HTTP servant à exécuter la méthode getHello.<br/>
Il existe toutes les méthodes HTTP: <b>@GET</b>, <b>@POST</b>, <b>@DELETE</b>, <b>@PUT</b>, …

Dans le constructeur de la classe, vous pouvez voir qu’il y a un seul paramètre HelloService.
Cela sert à demander au conteneur Nest IoC de nous fournir une instance d'un objet (HelloService).

---
title: Nest.js
layout: chapter_title
---

## Provider

Beaucoup de classes peuvent être considérées comme un provider (service/repository/factories/helper/…) (e.q. aux composants de Spring Boot).
Un provider peut être injecté comme une <b>dépendance</b>.

---

<div class="m-auto">

**./hello/hello.service.ts**

</div>

```ts
@Injectable()
export class HelloService {
    getHello(): string {
        return "Hello World !";
    }

    getHelloByName(name: string): string {
        return `Hello World ${name} !`;
    }
}
```

Dans cet exemple, j’utilise l’annotation <b>@Injectable</b> pour signaler que cette classe peut être managée par le conteneur Nest IoC (e.q @Component de Spring Boot).<br/>
Et je définis 2 méthodes pour renvoyer « Hello world », l’une avec le nom et l’autre sans.

---
title: Nest.js
layout: chapter_title
---

## Modules
Pour garder votre code <b>organisé</b>, vous pouvez utiliser les modules.<br/>

<div class="m-auto" scr="/images/nestjs-modules.png" />

---

Par exemple, si vous définissez un ensemble de classe permettant de gérer des utilisateurs, vous devez créer un module et y placer toutes vos classes dedans.

Chaque application a un <b>module racine</b> défini dans le fichier « app.module.ts ».
C ’est dans ce module que vous allez importer vos modules enfant (ex: User modules/Item module) et des modules externes (orm, hbs, …).

Un module est par défaut un <b>singleton</b>, ce qui permet d’utiliser la même instance d’une classe dans plusieurs modules l’ayant importé.
Un module utilisé dans plusieurs modules est appelé un <b>shared module</b>.

---
title: Nest.js
layout: chapter_title
---

### Exemple
Je créais les classes suivantes:
- HelloController: définie les endpoints
- HelloService: définie le code métier

Si je ne créer pas de nouveau module, à chaque fois que je souhaiterais utiliser mon code, je devrais importer chaque classe.

---

<div class="m-auto">

**./app.module.ts**

</div>

```ts
@Module({
  imports: [
    …
  ],
  controllers: [HelloContoller],
  providers: [HelloService],
})
export class AppModule {}
```

---

Alors que si je définis le module HelloModule, je n’aurai plus qu’a l’importer.

<div class="m-auto">

**./hello/hello.module.ts**

</div>

```ts
@Module({
  imports: [],
  controllers: [HelloController],
  providers: [HelloService],
  exports: []
})
export class HelloModule {}
```

---

<div class="m-auto">

**./app.module.ts**

</div>

```ts
@Module({
  imports: [
    …,
    HelloModule,
    …
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
```

> Si vous voulez avoir accès à HelloService, en important hello.module, il faut ajouter HelloService dans la liste des exports de votre module.

---
title: Nest.js
layout: chapter_title
---

## Pipes
Les pipes sont utilisés souvent de 2 manières :
- Pour <b>transformer</b> les données d’input en une forme plus adapté (e.g., un string deviens un integer)
- Pour <b>évaluer</b> les données d’entrée and les vérifier, et si elles sont valides, les laisse continuer sinon lance une exception.

<img class="m-auto" scr="/images/nestjs-pipes.png" />

---

> Il existe des Pipes prédéfinis:
> - ValidationPipe
> - ParseIntPipe
> - ParseFloatPipe
> - ParseBoolPipe
> - ParseArrayPipe
> - ParseUUIDPipe
> - ParseEnumPipe
> - DefaultValuePipe
> - ParseFilePipe
>
> [Voir documentation](https://docs.nestjs.com/pipes)

---

```ts
@Get(':id')
async findOne(@Param('id', ParseIntPipe) id: number) {
  return this.catsService.findOne(id);
}
```

---

### Exemple de Pipe
<div class="m-auto">

**CustomValidationPipe.ts**

</div>

```ts
import { ArgumentMetadata, BadRequestException } from "@nestjs/common";
import { Injectable, PipeTransform } from "@nestjs/common";
import { ObjectSchema } from "joi";

@Injectable()
export class CustomValidationPipe implements PipeTransform {
  constructor (private schema: ObjectSchema) {}

  transform(value: any, metadata: ArgumentMetadata) {
    const { error } = this.schema.validate(value);

    if(error) {
      throw new BadRequestException('Invalid Input Data');
    }

    return value;
  }
}
```

---

<div class="m-auto">

**EmployeeController.ts**

</div>

```ts
export class EmployeeController {
  @Post()
  @UsePipes(new CustomValidationPipe(createEmpSchema))
  async create(@Body() createEmpDTO: CreateEmpDTO) {
    console.log("Calling the Employee Create Service")
  }
}
```

---
title: Nest.js
layout: chapter_title
---

## Filtres

Les filtres vont vous permettre d’attraper les <b>exceptions</b> durant l’exécution de votre code et de renvoyer un message d’erreurs à l’utilisateur.

<img class="m-auto" scr="/images/nestjs-filters.png" />

---

### Exemple
```ts
if(error) {
  throw new HttpException();
}

// JSON user response: { "statusCode": 500, "message": "Internal server error" } 

```

---
title: Nest.js
layout: chapter_title
---

## Guards

Un Guard sert à une seul chose, vérifier si les requêtes HTTP entrantes ont le <b>droit d’accéder</b> au point d’entrée demandé.
Il faut que la requête respecte des <b>conditions</b> (ex: rôle/permissions/…) que vous pouvais définir vous-même. (~Spring security)

<img class="m-auto" src="/images/nestjs-guards.png" />

---

Possibilité de créer ses propres Guard, il faut <b>override</b> la fonction *canActivate* et renvoyer une Exception, Faux ou Vrai (ex: validateRequest).

```ts
@Injectable()
export class AuthGuard implements CanActivate {
  canActivate(context: ExecutionContext): boolean | Promise<boolean> | Observable<boolean> {
    const request = context.switchToHttp().getRequest();
    return validateRequest(request);
  }
}
```

---

Ensuite, il suffit de l’utiliser sur chaque contrôleur avec l’annotation <b>@UseGuards</b> avec en paramètre le type du guard ou nouvelle instance du guard.

```ts
@Controller('cats')
@UseGuards(RolesGuard)
export class CatsController {}
```

---

Ou alors en déclarant votre guard comme étant le guard de toute l’application (déclaration dans le module puis import de ce module).

```ts
import { Module } from '@nestjs/common';
import { APP_GUARD } from '@nestjs/core';
import { RolesGuard } from './roles.guard';

@Module({
  providers: [
    {
        provide: APP_GUARD,
        useClass: RolesGuard
    }],
})
export class RolesModule {}
```

---
title: Nest.js
layout: chapter_title
---

## Tests
### Jest Unit test

<div class="m-auto">

**hello.controller.spec.ts**

</div>

```ts
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
});
```

---

### Jest E2E test

<div class="m-auto">

**app.e2e-spec.ts**

</div>

```ts
describe('AppController (e2e)', () => {
  let app: INestApplication;

  beforeEach(async () => {
    const moduleFixture: TestingModule = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();
    app = moduleFixture.createNestApplication();
    await app.init();
  });

  describe('Hello test', () => {
    it('/hello (GET)', () => {
      return request(app.getHttpServer())
        .get('/hello')
        .expect(200)
        .expect('Hello World!');
    });
  })
});
```

---
title: Nest.js
layout: chapter_title
---

### Documentation

[docs.nestjs.com](https://docs.nestjs.com/)

<img class="m-auto" scr="/images/nestjs-doc.png" />