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
- Méthodologies

---
<!-- header: 'Infrastructure' -->

## Serveur / Virtualisation / Conteneurisation

### Serveur

### Virtualisation

### Conteneurisation

---
<!-- header: 'Infrastructure' -->

![bg 100%](resources/images/docker-principes.png)

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

<details>
<summary>Exemple d'utiliation de K8S</summary>

```YML
apiVersion: v1
kind: Namespace
metadata:
  name: intes
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: api-part-deployment
  namespace: intes
  labels:
    app: api-part
spec:
  selector:
    matchLabels:
      app: api-part
  template:
    metadata:
      labels:
        app: api-part
    spec:
      containers:
        - name: api-part
          image: haingue/api-part:arm64
          imagePullPolicy: "Always"
          ports:
            - containerPort: 8080
          resources:
            limits:
              memory: "128Mi"
              cpu: "500m"
---
apiVersion: autoscaling/v2beta2
kind: HorizontalPodAutoscaler
metadata:
  name: api-part-scale
  namespace: intes
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: api-part-deployment
  minReplicas: 1
  maxReplicas: 3
  metrics:
    - type: Resource
      resource:
        name: cpu
        target:
          type: Utilization
          averageUtilization: 80
    - type: Object
      object:
        metric:
          name: requests-per-second
        describedObject:
          apiVersion: networking.k8s.io/v1beta1
          kind: Ingress
          name: api-part-ingress
        target:
          type: Value
          value: 500
---
apiVersion: v1
kind: Service
metadata:
  name: api-part-service
  namespace: intes
  labels:
    app: api-part
spec:
  ports:
    - port: 8080
      protocol: TCP
  selector:
    app: api-part
---
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: api-part-ingress
  namespace: intes
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  rules:
    - host: api-part.imt.com
      http:
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: api-part-service
                port:
                  number: 8080
```
</details>

---
<!-- header: 'Infrastructure' -->

### Infrastructure-as-code

<center>

![height:400px](resources/images/infra-as-code-kubernetes.png)

</center>

---
<!-- header: 'Infrastructure' -->

### Infrastructure locale ou sur le cloud ?

![bg](resources/images/architecture-local-or-cloud.webp)

---
<!-- header: 'Architectures d'applications' -->
## Pratiques de développement

---
<!-- header: 'Architectures d'applications' -->
### Test Driven Development
Permet de garantir le bon fontionnement d'une application et d'empécher la regression des versions de l'application.

<center>

![height:460px](resources/images/architecture-tdd.gif)

</center>

---
<!-- header: 'Architectures d'applications' -->

### Domain Driven Development

<center>

![height:460px](resources/images/architecture-ddd.jpg)

</center>

---
<!-- header: 'Architectures d'applications' -->

### Behavior Driven Development

<center>

![](resources/images/architecture-bdd.png)

</center>

---
<!-- header: 'Architectures d'applications' -->

## Type d'application
### Statefull
Application gardant un état interne, ce qui la rend difficile à répliquer.
Ex: sessions utilistateur

### Stateless
Application sans état, ce qui la rend facile à répliquer.

---
<!-- header: 'Architectures d'applications' -->

## Architecture d'application
### Monolith VS Micro-service

<center>

![height:400px](resources/images/monolith-vs-microservice.jpg)

</center>

---

### Serverless
L'informatique serverless est un modèle de développement cloud-native qui permet aux développeurs de créer et d'exécuter des applications sans avoir à gérer des serveurs.

> C'est comme créer une lambda expression

---
<!-- header: 'Authentification' -->

## Outils
### Active Directory
Annuaire d'utilisateur

### SSO
Sigle-Sign-on permet de se connecter à toutes les applications autorisé avec un seul compte

### RBAC
Permet de définir les droits par utilisateur.
Un droit = une fonctionnalité

---
<!-- header: 'Big data' -->
## Data & Big data

### ETL

<center>

![](resources/images/etl.png)

</center>

---

### DataPlatforme

<center>

![height:600px](resources/images/data-platform.png)

</center>

---
<!-- header: 'Méthodologies' -->
## Méthodologies

### Agile & Scrum
<center>

![height:500px](resources/images/architecture-agiles.png)

</center>

---
<!-- header: 'Méthodologies' -->

### DevOps

<center>

![height:500px](resources/images/devops.svg)

</center>

---

<center>

![height:700px](resources/images/devops-technologies.jpeg)

</center>

---

### DevSecOps

<center>

![height:500px](resources/images/devsecops.webp)

</center>

---

## GitOps

<center>

![height:500px](resources/images/gitops.jpg)

</center>

---

<center>

![height:650px](resources/images/gitops-technologies.webp)

</center>

---
<!-- header: 'Conclusion' -->

## Exemple

<center>

![height:600](resources/images/architecture-microservice.png)

</center>

---

# Pratiquer

[Redhat developers: environnement gratuit et complet](https://developers.redhat.com/)

[Kubernetes: tutoriels](https://kubernetes.io/fr/docs/tutorials/)
[Devops: document microsoft pour microsoft Azure](https://azure.microsoft.com/fr-fr/solutions/devops/tutorial)
[Devoxx: conférences de présentation et retours d'expérience](https://www.youtube.com/@DevoxxFRvideos)

[Cheatsheets Google](https://googlecloudcheatsheet.withgoogle.com/)