
Suejt : application evenementiel

Roles:
- Administrateur
- Organisateur
- Particiant
(- Propriétaire)

Capacités:
- Rendre plus facile l'organisation d'event
  - Oraganiser des events
  - Gérer la vente des tickets
  - Chercher du personel
- Rendre plus facile l'accès à des events
  - Chercher des events
  - Acheter des tickets
  - Revente des tickets
- Augmenter la confiance envers les organisateur
  - Système de votes

# Context
## Global
```plantuml
@startuml
left to right direction
actor Participant as p
actor Organisateur as o
[Evennement service] as e
[Ticket service] as t
[Note service] as n

rectangle Evennementiel as app {
  usecase (Create event) as f1
  usecase (Suivre une vente) as f2
  usecase (Annluer un event) as f3
  usecase (Acheter un ticket) as f4
  usecase (Revendre un ticket) as f5
  usecase (Trouver un event) as f6
  usecase (Evaluer un event) as f7
}
o --> f1
o --> f2
o --> f3
p --> f4
p --> f5
p --> f6
p --> f7
f1 --> e
f2 --> e
f3 --> e
f4 --> t
f5 --> t
f6 --> e
f7 --> n
e --> t
e --> n

@enduml
```

## Details
### Rechercher les events les mieux notés
```plantuml
@startuml
participant Participant as p
participant Organisateur as o
entity "Evennement service" as e
entity "Ticket service" as t
entity "Note service" as n
p --> e : Recherche des events
e --> n : Demande les events les mieux notés
n --> e : Envoi une liste d'events trié
e --> p : Envoi le détails des events les mieux notés
@enduml
```
### Annuler un event
```plantuml
@startuml
participant Participant as p
participant Organisateur as o
entity "Evennement service" as e
entity "Ticket service" as t
entity "Note service" as n
o --> e : Annule un event
e --> t : Supprime tous les tickets vendus
t --> p : Notifi tous les participant
t --> p : Rembourse tous les participants
'e --> n : Supprime les notes
e --> e : Supprime l'event
@enduml
```

# Motivations
```plantuml
@startuml
archimate #Motivation "Augmenter la confiance envers les organisateur" as trust <<motivation-driver>>
archimate #Motivation "Rendre plus facile l'achat/revente des tickets" as purchase <<motivation-driver>>

archimate #Motivation "Rendre l'accès aux evenement facile" as simple_access <<motivation-goal>>
archimate #Motivation "Réduire les fraudes" as fraud <<motivation-goal>>

trust --> simple_access
trust --> fraud
purchase --> simple_access
@enduml
```

# Capacity

```plantuml
@startuml
archimate #Business "Event app" as app <<business-service>>
archimate #Business "Gestion des events" as events <<business-process>>
archimate #Business "Gestion des tickets" as tickets <<business-process>>
archimate #Business "Evaluation des organisateur" as evaluate <<business-process>>

app --> events
app --> tickets
app --> evaluate
@enduml
```

# Architecture
```plantuml
@startuml
rectangle AppUI as app_ui
rectangle AppBackend as app_backend
collections EventService as e_service
database EventDatabase as e_bdd
collections TicketService as t_service
database TicketDatabase as t_bdd
collections MarkService as m_service
database MarkDatabase as m_bdd

app_ui <--> app_backend
app_backend <--> e_service
app_backend <--> t_service
app_backend <--> m_service
e_service -- e_bdd
t_service -- t_bdd
m_service -- m_bdd
@enduml
```

## EventService
### Entitée
| Event |
| --- |
| __id__: UUID |
| title: String |
| description: String |
| ticketPrice: double |
| organisator: UUID |
| location: Location |
| datetime: LocalDatetime |

| Location |
| --- |
| __address__: String |
| capacity: int |


### Contrat de service
| Method | url | body | Result | Description |
| --- | --- | --- | --- | --- |
| GET | /event | [ ]: Collection\<Event> | 200 ou 204 | List 10 events |
| GET | /event/{id} | {}: Event | 200 ou 404 | Renvoi un event sinon error |
| POST | /event | {}: Event | 201 ou 400 ou 401 | Créer un event |
| PUT | /event | {}: Event | 200 ou 400 ou 401 ou 404 | Modifier un event |
| DELETE | /event/cancel/{id} | | 200 ou 401 ou 404 | Annuler un event |

## TicketService
### Entitée
| Ticket |
| --- |
| __id__: UUID |
| event: Envent |
| participant: UUID |
| paymentDatetime: LocalDatetime |
| creationDatetime: LocalDatetime |

| Event |
| --- |
| __id__: UUID |
| title: String |
| ticketPrice: double |
| organisator: UUID |
| datetime: LocalDatetime |
