# Sujet TP – Conception de projet 2025-2026

Dans ce TP commun, nous allons définir un projet, le décrire et concevoir son architecture pour nos prochains TP de Spring-Boot et NestJS.

<br><br>

## Objectif

Définir un projet informatique complet:

1. Imaginer le projet
2. Décrire le fonctionnement
3. Détailler le fontionnement
4. Lister les objectifs/motivations du projet
5. Lister les capacités du porjet
6. Concevoir l'architecture

# Context

## Diagram de cas d'utilisation

```plantuml
@startuml
left to right direction
actor Student as s
actor Company as c

[JobOffer service] as d
[JobDemand service] as o

rectangle StudentCareerApp as app {
  usecase (Search offer) as f1
  usecase (Publish job demand) as f2
  usecase (Valid job offer) as f3
  usecase (Publish job offer) as f4
  usecase (Valid job demand) as f5
}
s --> f1
s --> f2
s --> f3
c --> f4
c --> f5

f1 -- d
f2 -- o
f3 -- d
f4 -- d
f5 -- o

@enduml
```

## Details

### Publier une offre

> User story: "En tant d'**étudiant**, je veux **publier une offre à partir d'un formulaire**"

```plantuml
@startuml
participant Company as s
participant "JobOffer\nAPI" as o
participant "JobDemand\nAPI" as d
s --> d : Envoi les informations
d --> d : Vérifie les informations de l'offre
alt Information non valid
  d --> s : Retourn la liste des erreurs
else Offre valide
  d -> d : Sauvegarde l'offre
end
@enduml
```
### Valider une offre

```plantuml
@startuml
participant Student as s
participant Company as c
participant "JobOffer\nAPI" as o
participant "JobDemand\nAPI" as d

s --> o : Accepte l'offre
o --> d : Notifie que l'étudient sera occupé\npendant la période de l'offre
d --> d : Supprime les demandes pendant la période
o --> o : Mets à jour l'offre
o --> c : Informe l'entrerpise qu'un étudient à accepté l'offre
o --> e : Envoi un mail de confirmation\navec les informations pertinentes
@enduml
```

# Motivations

```plantuml
@startuml
archimate #Motivation "Travailler" as d1 <<motivation-driver>>
archimate #Motivation "Rendre service" as d2 <<motivation-driver>>

archimate #Motivation "Gagner de l'expérience" as m1 <<motivation-goal>>
archimate #Motivation "Gagner de l'argent" as m2 <<motivation-goal>>
archimate #Motivation "Chercher des futures collaborateurs" as m3 <<motivation-goal>>

@enduml
```

# Capacity

```plantuml
@startuml
archimate #Business "Student career" as app <<business-service>>
archimate #Business "Gestion des offress" as offres <<business-process>>
archimate #Business "Gestion des demande" as demandes <<business-process>>

app --> offres
app --> demandes
@enduml
```

# Architecture

```plantuml
@startuml
rectangle StudentCareerFrontend as app_ui
rectangle StudentCareerBackend as app_backend
collections JobOfferService as e_service
database JobOfferDatabase as e_bdd
collections JobDemandService as t_service
database JobDemandDatabase as t_bdd

app_ui <--> app_backend
app_backend <--> e_service
app_backend <--> t_service
e_service -- e_bdd
t_service -- t_bdd
@enduml
```

## JobOfferService

### Entitée

<center>

```plantuml
@startuml
Class JobOffer {
  **uid**: UUID
  title: String
  startDate: LocalDate
  endDate: LocalDate
  startDayTime: LocalTime
  endDayTime: LocalTime
  student: UUID
  expirationDays: int
  creationTimestamp: Instant
}
Class Company {
  name: String
  address: String

}
JobOffer *-- Company : owner
@enduml
```

</center>

###  Contrat de service

| Method | url | request body| response body | Result | Description |
| --- | --- | --- | --- | --- | --- |
| GET | /job-offer | | [ ]: Collection\<JobOffer> | 200 ou 204 | List 10 job offers |
| GET | /job-offer | params: title | []: Collection<JobOffer> | 200 ou 201 | Permet de chercher toutes les offres par titre |
| GET | /job-offer | params: company | []: Collection<JobOffer> | 200 ou 201 | Renvoi la liste des offre de l'entrerpies |
| GET | /job-offer/{uid} | | {}: JobOffer | 200 ou 404 | Renvoi une offer sinon error |
| POST | /job-offer | {}: JobOffer | {}: JobOffer | 201 ou 400 ou 401 | Créer une offer |
| PUT | /job-offer | {}: JobOffer | {}: JobOffer | 200 ou 400 ou 401 ou 404 | Modifier une offer |
| DELETE | /job-offer/{uid} | | 200 ou 401 ou 404 | Annuler une offer |

## JobDemandService

### Entitée


<center>

```plantuml
@startuml
Class JobDemand {
  **uid**: UUID
  startDate: LocalDate
  endDate: LocalDate
  startDayTime: LocalTime
  speciality: Speciality
  endDayTime: LocalTime
  creationDatetime: Instant
  expirationDays: int
  creationTimestamp: Instant
}
Class Student {
  **email**: UUID
  firstname: String
  lastname: String
  studentNumber: String
  lastLoginTimestamp: Instant
}
Class Speciality {
  **name**: String
}
Enum SpecialityCategory {
  Development
  Project managment
  Legal
}
JobDemand *-- Student : requestor
Student *-- Speciality : speciality
Speciality <|-- SpecialityCategory : category
@enduml
```

</center>

###  Contrat de service

| Method | url | request body| response body | Result | Description |
| --- | --- | --- | --- | --- | --- |
| GET | /job-demand | | [ ]: Collection\<JobDemand> | 200 ou 204 | List 10 job demands |
| GET | /job-demand | student | [ ]: Collection\<JobDemand> | 200 ou 204 | Renvoi la liste de demandes de l'étudient |
| GET | /job-demand/{uid} | | {}: JobDemand | 200 ou 404 | Renvoi un job demand sinon error |
| POST | /job-demand | {}: JobDemand | {}: JobDemand | 201 ou 400 ou 401 | Créer un job demand |
| PUT | /job-demand | {}: JobDemand | {}: JobDemand | 200 ou 400 ou 401 ou 404 | Modifier un job demand |
| DELETE | /job-demand/{uid} | | 200 ou 401 ou 404 | Annuler un job demand |

# Mockup

## JSON-Server
Vous pouvez utiliser [json-server](https://www.npmjs.com/package/json-server) pour créer une première version minimaliste de vos micro-service pour pouvoir créer rapidement une maquette du projet.
![](./event-app_mockup.json)

```shell
json-server ./event-app_mockup.json
```
