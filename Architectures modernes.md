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
header: "INTES - Architectures modernes"
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

![](resources/images/modern-archi-presentation.png)

# Architectures modernes

### INTES

### 2023-2024

  </div>
  
  <div class="row-span-1 row-start-3">

###### Fabien HAINGUE

  </div>
</div>

---

## Sommaire

- Infrastructure
- Architectures d'applications
- Authentification
- Big data
- Ops

---
<!-- header: 'Infrastructure' -->

![bg 100%](resources/images/docker-principes.png)

---
<!-- header: 'Infrastructure' -->

## Serveur / Virtualisation / Conteneurisation

### Serveur

### Virtualisation

### Conteneurisation

---
<!-- header: 'Infrastructure' -->

## Orchestration

Déploiement
- Declaration

Haute disponibilité
- Replication
- Distribution
- Auto-healing

Outils
- Security
- Isolation
- Telemetry
- ...

<!-- TODO add image of k8s implementation logo -->

---

### Kubernetes

<center>

![](resources/images/architecture%20-%20kubernetes.png)

</center>

---
<!-- header: 'Infrastructure' -->

### Infrastructure-as-code

---
<!-- header: 'Architectures d'applications' -->

## Type d'application
### Statefull
### Stateless

---
<!-- header: 'Architectures d'applications' -->

## Architecture d'application
### Monolythe

### Micro-service

### Serverless

---
<!-- header: 'Authentification' -->

## Active Directory

## SSO

## RBAC

---
<!-- header: 'Big data' -->

---
<!-- header: 'Ops' -->

## DevOps
### DevSecOps

## GitOps
