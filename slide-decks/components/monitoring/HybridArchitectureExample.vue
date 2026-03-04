<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <!-- Titre -->
    <h1 class="text-3xl font-bold text-gray-800 mb-2 text-center">
      🌉 Architecture Hybride : Exemple avec Kubernetes
    </h1>
    <p class="text-center text-gray-600 mb-8 text-sm">
      Combinaison de <strong>Pull</strong> (Prometheus) et <strong>Push</strong> (Fluentd, Jaeger) pour une observabilité complète.
    </p>

    <!-- Schéma d'architecture -->
    <div class="bg-white p-6 rounded-lg shadow-md">
      <div class="flex justify-center mb-4">
        <img
          src="https://via.placeholder.com/600x300/3B82F6/FFFFFF?text=Kubernetes+Cluster"
          alt="Kubernetes Cluster"
          class="rounded-lg"
        />
      </div>

      <!-- Légende des composants -->
      <div class="grid grid-cols-1 md:grid-cols-3 gap-4 mt-6">

        <!-- 1. Prometheus (Pull) -->
        <div class="bg-gray-50 p-4 rounded-lg">
          <div class="flex items-center mb-2">
            <span class="text-blue-500 text-xl mr-2">🔄</span>
            <h3 class="font-semibold text-gray-700">Prometheus (Pull)</h3>
          </div>
          <p class="text-sm text-gray-600 mb-2">
            <strong>Scrape</strong> les métriques des pods toutes les 15s.
          </p>
          <ul class="text-xs list-disc pl-5 space-y-1">
            <li>Endpoint <code>/metrics</code> exposé par chaque pod.</li>
            <li>Découverte automatique via <strong>Prometheus Operator</strong>.</li>
            <li>Stockage des métriques (rétention : 30j).</li>
          </ul>
          <div class="mt-3 text-xs bg-blue-50 p-2 rounded">
            Exemple : <code>kube_state_metrics</code> pour les métriques Kubernetes.
          </div>
        </div>

        <!-- 2. Fluentd (Push) -->
        <div class="bg-gray-50 p-4 rounded-lg">
          <div class="flex items-center mb-2">
            <span class="text-green-500 text-xl mr-2">📤</span>
            <h3 class="font-semibold text-gray-700">Fluentd (Push)</h3>
          </div>
          <p class="text-sm text-gray-600 mb-2">
            <strong>Collecte les logs</strong> des pods et les envoie à ELK.
          </p>
          <ul class="text-xs list-disc pl-5 space-y-1">
            <li>Déployé en <strong>DaemonSet</strong> (1 pod par nœud).</li>
            <li>Filtrage et parsing des logs (ex: JSON, regex).</li>
            <li>Bufferisation pour éviter les pertes.</li>
          </ul>
          <div class="mt-3 text-xs bg-green-50 p-2 rounded">
            Exemple : Logs des conteneurs → <code>stdout</code> → Fluentd → ELK.
          </div>
        </div>

        <!-- 3. Jaeger (Push) -->
        <div class="bg-gray-50 p-4 rounded-lg">
          <div class="flex items-center mb-2">
            <span class="text-purple-500 text-xl mr-2">🔍</span>
            <h3 class="font-semibold text-gray-700">Jaeger (Push)</h3>
          </div>
          <p class="text-sm text-gray-600 mb-2">
            <strong>Traces distribuées</strong> pour le debugging.
          </p>
          <ul class="text-xs list-disc pl-5 space-y-1">
            <li>Instrumentation via <strong>OpenTelemetry</strong>.</li>
            <li>Traçage des requêtes entre microservices.</li>
            <li>Visualisation des <strong>waterfall diagrams</strong>.</li>
          </ul>
          <div class="mt-3 text-xs bg-purple-50 p-2 rounded">
            Exemple : Suivre une requête de <code>/checkout</code> à travers 5 services.
          </div>
        </div>
      </div>

      <!-- 4. Grafana (Visualisation) -->
      <div class="mt-4 bg-gray-50 p-4 rounded-lg">
        <div class="flex items-center mb-2">
          <span class="text-indigo-500 text-xl mr-2">📈</span>
          <h3 class="font-semibold text-gray-700">Grafana (Visualisation)</h3>
        </div>
        <p class="text-sm text-gray-600 mb-2">
          <strong>Agrège et affiche</strong> les données de Prometheus, Loki, et Jaeger.
        </p>
        <ul class="text-xs list-disc pl-5 space-y-1">
          <li>Dashboards par équipe (devs, ops, business).</li>
          <li>Alertes configurées via <strong>Alertmanager</strong>.</li>
          <li>Intégration avec <strong>Slack/PagerDuty</strong>.</li>
        </ul>
        <div class="mt-3 text-xs bg-indigo-50 p-2 rounded">
          Exemple : Dashboard "API Health" avec latence, erreurs, et logs.
        </div>
      </div>

      <!-- Schéma simplifié -->
      <div class="mt-6 flex justify-center">
        <div class="text-center">
          <div class="flex justify-center space-x-8 mb-4">
            <div class="text-center">
              <div class="w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center mb-2">
                <span class="text-blue-500 text-xl">🔄</span>
              </div>
              <span class="text-xs font-medium">Prometheus</span>
            </div>
            <div class="text-center">
              <div class="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center mb-2">
                <span class="text-green-500 text-xl">📤</span>
              </div>
              <span class="text-xs font-medium">Fluentd</span>
            </div>
            <div class="text-center">
              <div class="w-16 h-16 bg-purple-100 rounded-full flex items-center justify-center mb-2">
                <span class="text-purple-500 text-xl">🔍</span>
              </div>
              <span class="text-xs font-medium">Jaeger</span>
            </div>
          </div>
          <div class="flex justify-center">
            <div class="w-24 h-8 bg-gray-200 rounded-full flex items-center justify-center font-bold text-sm">
              Kubernetes
            </div>
          </div>
          <div class="mt-4 flex justify-center">
            <div class="w-16 h-16 bg-indigo-100 rounded-full flex items-center justify-center mb-2">
              <span class="text-indigo-500 text-xl">📈</span>
            </div>
          </div>
          <span class="text-xs font-medium">Grafana</span>
        </div>
      </div>
    </div>

    <!-- Avantages de l'architecture hybride -->
    <div class="mt-6 p-4 bg-green-50 rounded-lg">
      <h2 class="font-semibold text-green-800 mb-2 flex items-center">
        <span class="text-green-500 mr-2 text-xl">✅</span>
        Pourquoi une Architecture Hybride ?
      </h2>
      <ul class="list-disc pl-5 space-y-1 text-sm text-gray-700">
        <li>
          <strong>Pull (Prometheus)</strong> : Idéal pour les métriques système (CPU, mémoire) et les environnements stables.
        </li>
        <li>
          <strong>Push (Fluentd/Jaeger)</strong> : Parfait pour les logs/traces et les données en temps réel.
        </li>
        <li>
          <strong>Flexibilité</strong> : Adapte-toi aux besoins (ex: ajouter un nouveau service sans reconfigurer Prometheus).
        </li>
        <li>
          <strong>Résilience</strong> : Réduit les risques de perte de données (buffering avec Fluentd).
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HybridArchitectureExample'
}
</script>
