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
    color: #6DB33F;
  }

  h2 {
    color: #6DB33F;
  }

  h3 {
    color: #B3512D;
  }
header: "INTES - Spring Boot"
footer: "Fabien HAINGUE"
---

<!-- _header: '' -->
<!-- _footer: '' -->
<!-- _paginate: skip -->
<div class="grid grid-cols-3 grid-flow-col gap-2 text-center">
  <div class="row-span-1">

![](../resources/images/IMT-logo.png)

  </div>

  <div class="row-span-1 row-start-3">
  
  ![](../resources/images/cc.svg)
  </div>
  
  <div class="row-span-3 col-span-3">

![](../resources/images/spring-boot-logo.png)

# Spring Boot

### INTES

### 2023-2024

  </div>
  
  <div class="row-span-1 row-start-3">

###### Fabien HAINGUE

  </div>
</div>

---

## Sommaire

- Java
  - Rappels
- Maven / Gradle
- Spring Boot
  - Spring Web
  - Spring Data
  - Spring Security
  - Tests
  - Bonus: Spring Webflux

---

<!-- header: 'Java' -->

## Fonctionnement

[Java](<https://fr.wikipedia.org/wiki/Java_(technique)>) est un langage de **programmation orienté objet**.
La version 1.0 de Java est sorti en **1995** par la société [Sun Microsystems](https://fr.wikipedia.org/wiki/Sun_Microsystems).

<br/>

A l'époque, Java se démarquait des autres langage car il était **indépendant de la plateforme matérielle**.
Pour cela Java s'abstrait du système d'exploitation grâce à la Java Virtual Machine.
![](../resources/images/java%20-%20abstraction.png)

---

## Editions

Java possède trois éditions

- **J**ava **R**untime **E**nvironment
  Contient uniquement l'environnement d'exécution de programmes Java.
- **J**ava **D**evelopment **K**it
  Contient lui-même le JRE et un ensemble d'outils de développement de programme Java.
- Documentation
  Contient toute la documentation au format HTML des API de Java

---

## Versions

<center>

| version  | release date | end of support |
| :------- | :----------: | :------------: |
| 8 (LTS)  |     2014     |      2030      |
| 11 (LTS) |     2018     |      2026      |
| 17 (LTS) |     2021     |      2029      |
| 21 (LST) |     2023     |       ?        |

</center>

---

## Programmation orienté objet

<center>

![](../resources/images/java%20-%20poo.png)

</center>
La POO permet de faciliter la vie des développeurs.<br/>
Plus longue a mettre en place mais permet de garder une bonne structure du code.

---

## Syntaxe

![bg right:70% 100%](../resources/images/java%20-%20syntaxe.png)

---

## Tips

### StringBuilder

```java
StringBuilder builder = new StringBuilder();
for (String address : addresses) {
  builder.append(address).append("\n");
}
String csv = builder.toString();
```

### Optional

```java
Optional<Object> wrapper = Optional.of(obj);
wrapper.ifPresent(object -> System.out.println(object));
Object resultObject = wrapper.orElseThrow(() -> new EntityNotFoundException());
```

### Stream

```java
stream.filter(obj -> obj != null).map(obj -> obj.hashCode()).collect(Collectors.toList());
```

### Remote debug

JVM options:
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005

---

<!-- header: 'Maven / Gradle' -->

## Maven / Gradle

Outils pour construire vos application Java et autre

- Automatiser certaines tâches : compilation, tests unitaires et déploiement des applications qui composent le projet
- Gérer des dépendances vis-à-vis des bibliothèques nécessaires au projet
- Générer des documentations concernant le projet

<center>

|  **Maven**   |      **Gradle**       |
| :----------: | :-------------------: |
|    Simple    | Performant / Flexible |
| Fichier .xml |    Fichier .gradle    |

</center>

---

## Démo

![](../resources/images/maven%20-%20example.gif)

---

<!-- header: 'Spring Boot' -->

## Spring framework

Historiquement, le framework Spring est le premier à avoir été créé. Spring permet de construire et définir l’infrastructure d’une application Java.

<center>

**2004 (1.0)** -> **2017 (5.0)**

</center>

Ensuite plusieurs projet ont émergés de l’équipe Spring:

- Spring Data pour le traitement de données
- Spring Web pour les applications Web.
- ...

---

## Principe de Spring

Conception basée sur l’inversion de contrôle ([IoC](https://gayerie.dev/docs/spring/spring/principe_ioc.html)).
C'est une façon de concevoir l'arichtecture d'une application en se basant sur le mécanisme objet de l’injection de dépendance.
<br/>

---

Couplage Fort

```java

public class ReservationSalleService {

  private ReservationSalleDao reservationSalleDao;

  public ReservationSalleService() {
    reservationSalleDao = new ReservationSalleDao();
  }

  public void reserver(ReservationSalle reservationSalle) {
    /** CODE **/

    // sauvegarder la réservation
    reservationSalleDao.sauver(reservationSalle);
  }

}
```

<br/>

Couplage faible

```java

public class ReservationSalleService {

  public void reserver(ReservationSalle reservationSalle) {
    /** CODE **/

    // sauvegarder la réservation
    ReservationSalleDao reservationSalleDao = ReservationSalleDaoLocator.get();
    reservationSalleDao.sauver(reservationSalle);
  }

}

```

<br/>

---
