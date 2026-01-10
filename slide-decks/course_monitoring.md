---
theme: default
title: Formation Monitoring
class: text-center
drawings:
  persist: false
transition: slide-left
mdc: true
seoMeta:
  ogImage: auto
plantUmlServer: https://www.plantuml.com/plantuml
download: true
layout: cover
level: 1
---

<Cover title="Formation Monitoring" logoUrl="/images/monitoring-logo.png" />

---
title: Introduction
layout: chapter_title
level: 2
---

<!--
<v-clicks>

- Timothée developpeur, déploit une nouvelle version de l'application à 15h00
- Tout ce passe bien
- 2H du matin, votre téléphone sonne, votre appli est down

</v-clicks>

<Note type="info" v-click>
  <b>Sais pas par où commencer, perte de temps => perte argent, ...</b>
</note>
-->

<MonitoringTimelineBadExemple />

<!--
« Imaginez la scène : il est 2h du matin. Vous venez de lancer une nouvelle version de votre application web, après des semaines de développement. Tout semblait parfait en environnement de test. Mais soudain, votre téléphone sonne. C’est votre collègue des opérations : “L’application est down. Les utilisateurs ne peuvent plus se connecter, les commandes ne passent plus, et les réseaux sociaux s’embrasent. On a des centaines de messages de clients en colère.” »

« Vous vous connectez en urgence. Le serveur répond, mais lentement. Les logs sont illisibles, noyés sous des milliers de lignes. Vous ne savez pas par où commencer : est-ce un problème de base de données ? De mémoire ? De requêtes trop lourdes ? Ou pire, une attaque ? »

« Pendant ce temps, chaque minute de downtime coûte des milliers d’euros à l’entreprise, et la réputation de votre équipe est en jeu. »

« Cette situation, beaucoup d’entre vous la vivront un jour. Et elle soulève une question cruciale : comment éviter de se retrouver dans le noir quand tout s’effondre ? »
-->

---
title: Problématique
layout: chapter_subtitle
level: 3
---

- 🕵️‍♂️ Invisibilité : « Vous ne voyez pas les signes avant-coureurs (ex : mémoire qui sature). »
- ⏳ Réactivité trop lente : « Quand l’utilisateur râle, il est déjà trop tard. »
- 🎯 Diagnostic impossible : « Sans données, vous devinez au lieu d’agir. »

<Note type="info" v-click>
  <b>Vous etes dans le noir</b>
</note>

<!--

Pourquoi ça arrive ?

-->

---
title: Solution
layout: chapter_subtitle
level: 3
---

- 📊 Anticiper : « Surveiller les métriques clés (CPU, temps de réponse, erreurs). »
- 🔍 Comprendre : « Corréler les logs et les métriques pour identifier la racine du problème. »
- ⚡ Agir : « Alertes ciblées + outils pour résoudre rapidement. »

<Note type="info" v-click>
  <b>C'est votre lampe torche</b>
</note>

---
title: Sommaire
layout: chapter_title
level: 2
---

## Ce que vous allez apprendre aujourd’hui

- Principes
- Métriques clés : « Quelles données surveiller ? (Latence, throughput, erreurs…). »
- Outils : « Prometheus, Grafana, Spring Boot Actuator, ELK Stack. »
- Alertes : « Comment configurer des seuils pertinents ? »
- Cas pratiques : « Debugger une panne en live. »


---
title: Principes
layout: chapter_title
level: 2
---

::left::

- Supervision
  - Etat actuel
  - Alertes

::right::

- Métrologie
  - Historique de mesure

<!--

« Le monitoring, ce n’est pas juste “regarder des graphiques”. C’est : »

-->


---
title: Les mesure clés 
layout: chapter_title
level: 2
---

::top::

- Latence
- Nombre d'erreur
- Trafique
- Saturation

::bottom::

<v-switch>
  <template #1>
  S1 -> s2 -> S3
  Latence
  </template>
  <template #2>
Nombre d'erreur
  S1 -> s2 -> S3
  </template>
  <template #3>
  S1 -> s2 -> S3
Trafique
  </template>
  <template #4>
  S1 -> s2 -> S3
Saturation
  </template>
</v-switch>

---
title: Methodologie 
layout: chapter_subtitle
level: 3
---

# DevOPS

<v-switch>
  <template #1>
    <img class="m-auto h-80" src="/images/devops.svg" />
  </template>
  <template #2>
    <img class="m-auto h-80" src="/images/devops-technologies-ex.png" />
  </template>
  <template #3>
    <img class="m-auto h-80" src="/images/devops-technologies.jpeg" />
  </template>
</v-switch>

---
title: DX&#58; Minimalisme & pertinence 
layout: chapter_subtitle
level: 3
---

<v-switch>
  <template #1>

  Mauvais monitoring
  - Liste complètes
  - Valeurs incohérentes
  - valeurs non significatives

  </template>
  <template #2>

  Bon monitoring
  - Mise en avant d'éléments
  - Icône simple

  </template>
  <template #3>

  Monitoring détaillé
  - Adapté à la technologie
  - Fait par et pour les experts

  </template>
</v-switch>

---
title: Outils
layout: chapter_title
level: 2
---

## Strategie

- Pull-based
- Push-based

[voir](https://www.alibabacloud.com/blog/pull-or-push-how-to-select-monitoring-systems_599007#:~:text=The%20Pull%2Dbased%20monitoring%20system,monitored%20objects%20actively%20push%20indicators.)

---
title: Centralisation des mesures
layout: chapter_subtitle
level: 3
---

# Prometheus

---
title: Analyses et alertes
layout: chapter_subtitle
level: 3
---

# Alertmanager

---
title: Visualisation
layout: chapter_subtitle
level: 3
---

# Grafana

---
title: Aller plus loin
layout: chapter_title
level: 2
---

- Trace
  - Opentelemetry
- E2E (playwright)
- Auto-healing

---
title: Questions ?
layout: impacting_message
level: 3
---

Merci !

Avez-vous des questions ?
