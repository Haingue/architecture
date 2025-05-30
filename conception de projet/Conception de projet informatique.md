---
marp: true
theme: default
paginate: true
style: |
  @import url('https://unpkg.com/tailwindcss@^2/dist/utilities.min.css');

  /* Change to the classic 4:3 slide */
  /*
  section {
    width: 960px;
    height: 720px;
  }
  */
  footer {
    text-align: center;
  }

  h1 {
    color: #FFBC1C
  }

  h2 {
    color: #402F07
  }

  h3 {
    color: #E6A819
  }
header: "INTES - Conception de projet informatique"
footer: "Fabien HAINGUE"
---

<!-- _header: '' -->
<!-- _footer: '' -->
<!-- _paginate: skip -->

<div class="grid grid-cols-3 grid-flow-col gap-2 text-center">
  <div class="row-span-1">

![](resources/images/IMT-logo.png)

  </div>

  <div class="row-span-1 row-start-3">
  
  ![](resources/images/cc.svg)
  </div>
  
  <div class="row-span-3 col-span-3">

![](resources/images/project-managment.png)

# Conception de projet informatique

### INTES

### 2024-2025

  </div>
  
  <div class="row-span-1 row-start-3">

###### Fabien HAINGUE

  </div>
</div>

---

# Sommaire
- Introduction
- Compréhension du problème
- Conception
- Gestion du projet

---
<!-- header: 'Introduction' -->
# Introduction
Pour réaliser un projet informatique avec succès, vous devez vous assurer de bien comprendre la problèmatique que vous essayer de résoudre, pour ensuite concevoir la meilleur solution adapté à votre client.

Pour cela, différents types de pratiques/documents peuvent vous aider à clarifier votre projet.

---
Ensuite, vous pourrais concevoir une architecture logiciel en prenant compte tout le cycle de vie du projet:
- Conception du projet
- Mise en place du projet
- Surveillance du projet
- Maintien opérationnel du projet
  - Réparation en cas de panne
  - Mise à niveau (vulnérabilités, mise à jour, ...)
- Arrêt/Remplacement du projet

> Tout au long du projet, la documentation sera une activité importante du projet.
Une bonne documentation peut vos aider à impliquer vos équipes, à concevoir la bonne solution, à transmettre vos connaissances, etc...

---
<!-- header: 'Compréhension du problème' -->
# Conception du projet
Dans le cadre d'un projet informatique, il faut prendre le temps necessaire pour la compréhension du problème, cela vous permettra de concevoir la meilleur solution au problème et en minimisant les imprévues.

<center>

![](../resources/images/preblems-solutions.png)

</center>

> Il est très rare de trouver la bonne soluton du premier coups.
> Certains choix de conception peuvent être plus lourd que d'autres, seul l'expérience peut vous aider (la votre ou celle de vos collègues)

---
<!-- header: 'Compréhension du problème' -->
## Compréhension du problème
Avant de se lancer dans de la conception d'application, il faut d'abord bien comprendre le problème que l'on essait de résoudre.

Le meilleurs moyen de bien comprendre une problèmatique est de discuter directement avec les personnes impliquées. Lors de ces échanges, il faut savoir identifier les points importants pouvant vous aiguiller sur la meilleur solution.

> Il est primordiale de s'imprégner du jargon venant du métier, cela pourra vous aider à mieux communiquer avec vos clients et de faciliter l'acception de votre nouvelle solution.

---
<!-- header: 'Compréhension du problème' -->
#### Exemple de présentation métiers
```
Nous sommes une entreprise qui vend des coffrets de livres.
Dans notre batiment principal qui se trouve en centre ville, nous avons notre ligne d'assemblage où des livres bien définis sont placés dans des boîtes. ces boîtes seront décorées puis emballées avant d'être envoyées dans toutes les librairies et grandes surfaces de France.

Notre mission est d'apporter les livres dans le batiment principal.
Par manque de place, on doit stocker nos livres dans des entrepôts à l'extérieur.
On a 10 entrepôts autour de l'entreprise.
Une équipe s'occupe de ranger les livres dans les entrepôts en attendant d'en avoir besoin.
Une autre équipe va chercher les livres au bon moment en fonction des commandes.
Et 2 chefs d'équipe supportent nos équipes.
```

---
<!-- header: 'Compréhension du problème' -->
## Description du métier
- Liste des roles

<div clasS="grid grid-cols-2">
  <div>

```plantuml
@startuml
left to right direction

:Stockeur:            --> (Stocke les livres dans les entrepôts)
:Livreur:             --> (Récupère les livres dans les entrepôts)
:Livreur:             --> (Livre les livres dans le batiment principal)
:ChefEquipe:          --> (Aide les stockeurs et les livreurs)
:ChefEquipe:          --> (Prend les décisions en cas de problèmes)

:Batiment Principal:  --> (Emplacement où les livres sont empaquetés)
:Entrepôt:            --> (Espace où stocker les livres)
    
@enduml
```
  </div>
  <div class="hidden">
    <img src="../resources/images/roles.png">
  </div>
</div>

---
<!-- header: 'Compréhension du problème' -->
- Liste des processus / gammes

| Processus                  |
|            ---             |
| Stockage de livre          |
| Livraison de livre         |
| Empaquetage des livres     |
| Décoration des boîtes      |
| Conditionnement des boîtes |

---
<!-- header: 'Compréhension du problème' -->
- Diagramme de séquence

<div class="grid grid-cols-2">
  <div>

**Reception**

```plantuml
@startuml
 --> Stockeur : Réceptionne les livres
Stockeur --> Stockeur : Se rend dans un entrepôt
Stockeur --> Entrepôt : Stocke les livres
@enduml
```
  </div>
  <div class="hidden">
    <img src="../resources/images/sequence-diagram.png">
  </div>
</div>

---
<!-- header: 'Compréhension du problème' -->
- Logigramme

<div class="grid grid-cols-2">
  <div>

**Livraison**

```plantuml
@startuml
start
:Commande de livre;
while (Choisir entrepôt)
  if(A de la place ?) then (oui)
    :Stocker livre;
    stop
  else (non)
  endif
endwhile (Plus d'entrepôt)
end
@enduml
```
  </div>
  <div class="hidden">
    <img src="../resources/images/logigram.png">
  </div>
</div>

---
<!-- header: 'Compréhension du problème' -->

## Obectifs

```
On a une cadence élevée pour assembler nos colis.
On perd beaucoup de temps à trouver quel entepôt contient les bons livres et à les chercher dans nos entrepôts.
Cela augmente les risques car on doit parcourir des longues distances dans des temps limités.
Nos équipes ont parfois du mal à noter les informations sur leur bloc notes, ils voudraient avoir un outil plus rapide.
Et nous avons également des objetifs écologiques, nous aimerions baisser notre impacte environnementale.
```

---
<!-- header: 'Compréhension du problème' -->

- Liste des Obectifs / Motivations
  - Objectifs globals
  - Objectifs soujacents

<div class="grid grid-cols-2">
  <div>

```plantuml
@startuml
archimate #Motivation "Réduire les erreurs pour trouver l'entrepôt" as r_error <<motivation-driver>>
archimate #Motivation "Réduire les erreurs de prise de note" as r_note <<motivation-driver>>
archimate #Motivation "Réduire les erreurs de stockage" as r_sto <<motivation-driver>>
archimate #Motivation "Réduire les erreurs de livraison" as r_del <<motivation-driver>>
archimate #Motivation "Réduire les risques d'accident" as safety <<motivation-goal>>
archimate #Motivation "Réduire la charge cognitive" as cogn <<motivation-goal>>
archimate #Motivation "Retirer l'utilisation de papier" as paper <<motivation-goal>>

r_sto --> r_error
r_del --> r_error
r_error ==> cogn
r_note ==> cogn
r_note ==> paper
r_error ==> safety
@enduml
```
  </div>
  <div class="hidden">
    <img src="../resources/images/objectifs-motivations.png">
  </div>
</div>

---
<!-- header: 'Compréhension du problème' -->
- Capacités
  - Lister les process / gammes


<div class="grid grid-cols-2">
  <div>

  ```plantuml
  @startuml
  archimate #Business "Approvisionnement de livres" as appro <<business-service>>
  archimate #Business "Gestion d'inventaire" as inv <<business-process>>
  archimate #Business "Stockage de livres" as sto <<business-process>>
  archimate #Business "Livraion de livres" as del <<business-process>>
  archimate #Business "Supporter une équipe" as andon <<business-process>>

  appro --> inv
  appro --> sto
  appro --> del
  appro --> andon
  @enduml
  ```
  </div>
  <div class="hidden">
    <img src="../resources/images/capacities.png">
  </div>
</div>

---
<!-- header: 'Compréhension du problème' -->
## Valider votre compréhension

<center>

![](../resources/images/comprehension-validation.png)

</center>

Lors de vos premiers échanges avec le métier, posez toutes vos questions.
Ensuite **documenter** votre compréhension du métier, de leurs problèmatiques et de leurs objectifs, ensuite **présentez** vos documents aux experts du métier pour vous assurer que votre compréhension soit la bonne.

---
<!-- header: 'Conception' -->
# Conception
## Cycle de vie

- Mise en place du projet
  - Environnement de Test / Production
- Surveillance du projet
  - Alertes
  - Monitoring
- Maintenance du projet
  - Panne
  - Mise à jour
- Arrêt du projet


---
<!-- header: 'Conception' -->
### Hybride diagramme

![](../resources/images/ISDM%20hybrid%20diagram.png)

---
<!-- header: 'Conception' -->
Dans cette architecture, j'ai fais le choix de créer une application par domaine.
Chaque application est composée d'une interface et d'une API qui utilise sa propre base de données, cela permet d'être plus résistent au panne et de partager plus facilement les informations avec d'autres systèmes.
Chaque composant d'une application peut avoir plusieurs instances en simultanés et sur différents servers.

Pour la gestion des accès/roles, j'utilise un **S**ingle **S**ign-**O**n ce qui permet d'avoir la même base d'utilisateur pour toutes les applications et demander à l'utilisateur de se connecter une seul fois.

---
<!-- header: 'Conception' -->
### Stack technologique
Mettre en place une applications demande beaucoup de ressources:
- Sauvegarde de code
- Outils de build
- Outils de déploiement
- Outils de monitoring
- Outils de d'alerte
- Stockage de données
- Outils de restauration
- ....

---
<!-- header: 'Conception' -->
Il est judicieux de définir une stack technique standard pour votre entreprise couvrant toutes les strapes de vos projets.

<div class="grid grid-cols-2">
<section class="w-full">
Ex:<br/>
- Source code managment: Github<br/>
- Application<br/>
&nbsp;&nbsp;&nbsp;&nbsp;- Backend: Spring Boot<br/>
&nbsp;&nbsp;&nbsp;&nbsp;- Frontend: React<br/>
- Data<br/>
&nbsp;&nbsp;&nbsp;&nbsp;- SQL: Postgresql (cluster mode)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;- NoSQL: Redis<br/>
&nbsp;&nbsp;&nbsp;&nbsp;- ETL: Pentaho Data Integration
</section>
<section class="w-full">
<br/>
- Shipment method: Container<br/>
&nbsp;&nbsp;&nbsp;&nbsp;- Image registry: Quay<br/>
&nbsp;&nbsp;&nbsp;&nbsp;- Hosting: Kubernetes<br/>
- CI/CD: Github workflow<br/>
- Monitoring<br/>
&nbsp;&nbsp;&nbsp;&nbsp;- Metrics: Prometheus + Grafana<br/>
&nbsp;&nbsp;&nbsp;&nbsp;- Metrics agregator: VictoriaMetrics<br/>
&nbsp;&nbsp;&nbsp;&nbsp;- Alertes: AlertManager
- ...
</section>
</div>

---
<!-- header: 'Conception' -->
<center>

![](../resources/images/tech-stack.jpeg)

</center>

---
<!-- header: 'Conception' -->
La liste peut être longue suivant le type de vos activités / clients.
Il est necessaire de définir une façon standard d'utiliser chaque technologie pour permettre de mutualiser les outils.
Cela vous permettra, par exemple, de mettre en place le monitoring de votre projet en quelques secondes car vous avez réutiliser la méthode standard définie par des personnes plus compètente sur ce sujet.

De plus, en utilisant le plus possible la même technologie, le maintient de celle-ci vous sera plus facile.

> Garder à l'esprit qu'une technologie doit être maintenu à jour pour réduire les failles de sécurité, quel doit être capable d'être redémarré/restaurée en cas de panne, ...

---
<!-- header: 'Conception' -->
### Mock-up
La partie la plus importante dans le cadre d'un projet applicatif est son interface, les autres composants ne sont que des composants techniques qui parleront aux équipes supports.
Il est donc important de présenter et valider des maquettes de l'interface aux différents experts du métier.

<center>

![bg left 100%](../resources/images/mockup.png)

</center>

---
<!-- header: 'Gestion de projet' -->
## Agile
#### Principes
<center>

![bg right 100%](../resources/images/architecture-agiles.png)

</center>

#### User story
![](../resources/images/user-story.png)

---
<!-- header: 'Gestion de projet' -->
#### Versionnement
- MVP: Minimum Viable Product
- MMP: Minimum Marketable Product
- MMR: Minimum Marketable Release

---
<!-- header: 'Finaliser un projet' -->
## Finaliser un projet 
#### SLA

---
<!-- header: 'Fin' -->

## Livres
- [Clean Code](https://www.amazon.fr/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882)
<center>

<img src="../resources/images/clean-codes.jpg" height="300">

</center>

- [ENI Architecture logicielle](https://www.editions-eni.fr/livre/architecture-logicielle-pour-une-approche-organisationnelle-fonctionnelle-et-technique-2e-edition-9782746099210)

---
<!-- header: 'Fin' -->
<center>

## Fin
Merci !
Avez-vous des questions ?

</center>