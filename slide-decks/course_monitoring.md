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

<h2 class="font-bold">Ce que vous allez apprendre aujourd’hui</h2>

- Principes
- Métriques clés : « Quelles données surveiller ? (Latence, throughput, erreurs…). »
- Mise en place : « Prometheus, Grafana, Spring Boot Actuator, ELK Stack. »
- Sujet du TP

<!--
- Principes
- Métriques clés : « Quelles données surveiller ? (Latence, throughput, erreurs…). »
- Mise en place : « Prometheus, Grafana, Spring Boot Actuator, ELK Stack. »
- Alertes : « Comment configurer des seuils pertinents ? »
- Cas pratiques : « Debugger une panne en live. »
-->

---
title: Principes
layout: chapter_title
level: 2
---

<!--
« Le monitoring, ce n’est pas juste “regarder des graphiques”. C’est : »
-->

<MonitoringPrincipes />

---
title: Les mesures clés 
layout: chapter_title
level: 2
---

::top::

<MonitoringGoldenSignals />

---
title: Methodologie 
layout: chapter_subtitle
level: 3
---

<v-switch>
  <template #1>
    <MonitoringMethodology />
  </template>
  <template #2>
    <MonitoringMethodologyDevOps />
  </template>
  <template #3>
    <img class="m-auto h-80" src="/images/devops-technologies-ex.png" />
  </template>
  <template #4>
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

  <BadMonitoringExample />

  </template>
  <template #2>

  <div>
    <div class="mt-8 p-4 rounded-lg text-sm">
      <h2 class="font-bold mb-2 text-red-600">❌ Pourquoi ce monitoring est mauvais ?</h2>
      <ul class="list-disc pl-5 space-y-1">
        <li><strong>Pas de légende</strong> : Les unités ("K", "%") sont ambiguës.</li>
        <li><strong>Mélange des statuts</strong> : Alertes critiques (🚨) et normales (✅) côte à côte sans hiérarchie.</li>
        <li><strong>Valeurs incompréhensibles</strong> : "4.2K" pour la mémoire ? "0.005K" erreurs ?</li>
        <li><strong>Surcharge visuelle</strong> : Trop de métriques non priorisées.</li>
        <li><strong>Pas de contexte</strong> : On ne sait pas ce que signifie "sys-prod-db-03-eu-west".</li>
      </ul>
    </div>
  </div>
  <div>
    <!-- Exemple de bonne pratique (optionnel, pour contraste) -->
    <div class="mt-6 p-3 rounded-lg text-center text-green-600 text-xs">
      ➡️ À la place, un bon monitoring devrait :
      <br />
      <strong>1. Clarifier les unités</strong> | <strong>2. Séparer les alertes critiques</strong> | <strong>3. Donner du contexte</strong> | <strong>4. Prioriser l'information</strong>
    </div>
  </div>

  </template>
  <template #3>

  <GoodMonitoringExample/>

  </template>
  <template #4>
  <div class="m-6 p-4 bg-blue-50 rounded-lg">
    <h2 class="font-bold text-blue-800 mb-2">✅ Pourquoi ce monitoring est efficace ?</h2>
    <ul class="list-disc pl-5 space-y-1 text-sm text-gray-700">
      <li><strong>Icônes météo</strong> : Statut visuel immédiat (☀️/⛅/🌧️/⚡/🌪️).</li>
      <li><strong>Hiérarchie claire</strong> : Couleurs et bordures pour prioriser.</li>
      <li><strong>Unités explicites</strong> : "4.2 Go / 16 Go" au lieu de "4.2K".</li>
      <li><strong>Contexte</strong> : Description du système et localisation.</li>
      <li><strong>Seuils visuels</strong> : Couleurs pour les valeurs anormales.</li>
    </ul>
  </div>
  </template>
  <template #5>
    <h1 class="text-2xl md:text-3xl font-bold text-gray-800 mb-2 text-center">
       🔎 Monitoring détailé
    </h1>
    <img class="m-auto h-80" src="/images/monitoring-grafana-board.png" />
  </template>
</v-switch>

---
title: Mise en place
layout: chapter_title
level: 2
---

<!--
## Strategie

- Pull-based
- Push-based

[voir](https://www.alibabacloud.com/blog/pull-or-push-how-to-select-monitoring-systems_599007#:~:text=The%20Pull%2Dbased%20monitoring%20system,monitored%20objects%20actively%20push%20indicators.)
-->

<MonitoringToolsOverview />

---
title: Strategie de collecte
layout: chapter_subtitle
level: 2
---

<PullVsPushComparison />

---
title: Strategie de collecte hybride
layout: chapter_subtitle
level: 2
---

<!-- Stratégie hybride -->
<div class="m-6 p-4 bg-green-50 rounded-lg">
  <h2 class="font-semibold text-green-800 mb-2 flex items-center">
    <span class="text-green-500 mr-2 text-xl">🔄📤</span>
    Stratégie Hybride (Recommandée)
  </h2>
  <p class="text-sm text-gray-700">
    Dans la pratique, la plupart des systèmes modernes <strong>combinent pull et push</strong> :
  </p>
  <ul class="list-disc pl-5 mt-2 text-sm text-gray-700">
    <li>
      <strong>Pull</strong> pour les métriques système (Prometheus → Kubernetes pods).
    </li>
    <li>
      <strong>Push</strong> pour les logs (Fluentd → ELK) et traces (Jaeger).
    </li>
  </ul>
</div>

---
title: Étapes
layout: chapter_subtitle
level: 2
---

<MonitoringStrategySteps />

---
title: Sujet TP
layout: chapter_title
level: 2
---

Mise en place d'un monitoring complet basé sur Prometheus/Grafana/AlertManager pour assurer le bon fonctionnement d'un microservice Spring Boot.

<img class="m-x-auto mt-6 w-xl" src="/images/monitoring-architecture.png" />

<!-- ---
title: Centralisation des mesures
layout: chapter_subtitle
level: 3

# Prometheus

title: Analyses et alertes
layout: chapter_subtitle
level: 3

# Alertmanager

title: Visualisation
layout: chapter_subtitle
level: 3

# Grafana -->

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
