<template>
  <div class="p-6 min-h-screen">
    <!-- Titre -->
    <h1 class="text-3xl font-bold text-gray-800 mb-4 text-center">
      🛠️ Les 8 Types d'Outils de Monitoring
    </h1>

    <!-- Grille des outils -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">

      <!-- 1. Collecteurs de Métriques -->
      <div class="bg-gray-50 p-4 rounded-lg shadow-md border-l-4 border-blue-500">
        <div class="flex items-center mb-2">
          <span class="text-2xl mr-2">📊</span>
          <h2 class="font-semibold text-gray-700">Collecteurs de Métriques</h2>
        </div>
        <p class="text-sm text-gray-600 mb-3">
          Récupèrent des données quantitatives (CPU, latence, etc.).
        </p>
        <div class="text-xs">
          <div class="flex justify-between mb-1">
            <span class="font-medium">Prometheus</span>
            <span class="text-blue-500">Pull</span>
          </div>
          <div class="flex justify-between mb-1">
            <span class="font-medium">StatsD</span>
            <span class="text-green-500">Push</span>
          </div>
          <div class="flex justify-between">
            <span class="font-medium">Telegraf</span>
            <span class="text-purple-500">Hybride</span>
          </div>
        </div>
      </div>

      <!-- 2. Centralisation des Logs -->
      <div class="bg-gray-50 p-4 rounded-lg shadow-md border-l-4 border-green-500">
        <div class="flex items-center mb-2">
          <span class="text-2xl mr-2">📜</span>
          <h2 class="font-semibold text-gray-700">Centralisation des Logs</h2>
        </div>
        <p class="text-sm text-gray-600 mb-3">
          Agrègent et analysent les logs (fichiers, événements).
        </p>
        <div class="text-xs">
          <div class="flex justify-between mb-1">
            <span class="font-medium">ELK Stack</span>
            <span class="text-green-500">Push</span>
          </div>
          <div class="flex justify-between mb-1">
            <span class="font-medium">Loki</span>
            <span class="text-green-500">Push</span>
          </div>
          <div class="flex justify-between">
            <span class="font-medium">Fluentd</span>
            <span class="text-green-500">Push</span>
          </div>
        </div>
      </div>

      <!-- 3. Tracing Distribué -->
      <div class="bg-gray-50 p-4 rounded-lg shadow-md border-l-4 border-purple-500">
        <div class="flex items-center mb-2">
          <span class="text-2xl mr-2">🔍</span>
          <h2 class="font-semibold text-gray-700">Tracing Distribué</h2>
        </div>
        <p class="text-sm text-gray-600 mb-3">
          Suivent une requête à travers plusieurs services.
        </p>
        <div class="text-xs">
          <div class="flex justify-between mb-1">
            <span class="font-medium">Jaeger</span>
            <span class="text-purple-500">Push</span>
          </div>
          <div class="flex justify-between mb-1">
            <span class="font-medium">Zipkin</span>
            <span class="text-purple-500">Push</span>
          </div>
          <div class="flex justify-between">
            <span class="font-medium">OpenTelemetry</span>
            <span class="text-purple-500">Hybride</span>
          </div>
        </div>
      </div>

      <!-- 4. Alerting -->
      <div class="bg-gray-50 p-4 rounded-lg shadow-md border-l-4 border-red-500">
        <div class="flex items-center mb-2">
          <span class="text-2xl mr-2">🚨</span>
          <h2 class="font-semibold text-gray-700">Alerting</h2>
        </div>
        <p class="text-sm text-gray-600 mb-3">
          Déclenchent des alertes et notifient les équipes.
        </p>
        <div class="text-xs">
          <div class="flex justify-between mb-1">
            <span class="font-medium">Alertmanager</span>
            <span class="text-blue-500">Pull</span>
          </div>
          <div class="flex justify-between mb-1">
            <span class="font-medium">PagerDuty</span>
            <span class="text-red-500">SaaS</span>
          </div>
          <div class="flex justify-between">
            <span class="font-medium">Opsgenie</span>
            <span class="text-red-500">SaaS</span>
          </div>
        </div>
      </div>

      <!-- 5. Visualisation -->
      <div class="bg-gray-50 p-4 rounded-lg shadow-md border-l-4 border-indigo-500">
        <div class="flex items-center mb-2">
          <span class="text-2xl mr-2">📈</span>
          <h2 class="font-semibold text-gray-700">Visualisation</h2>
        </div>
        <p class="text-sm text-gray-600 mb-3">
          Affichent les données sous forme de graphiques.
        </p>
        <div class="text-xs">
          <div class="flex justify-between mb-1">
            <span class="font-medium">Grafana</span>
            <span class="text-indigo-500">Pull/Push</span>
          </div>
          <div class="flex justify-between mb-1">
            <span class="font-medium">Kibana</span>
            <span class="text-green-500">Push</span>
          </div>
          <div class="flex justify-between">
            <span class="font-medium">Datadog</span>
            <span class="text-red-500">SaaS</span>
          </div>
        </div>
      </div>

      <!-- 6. Automatisation -->
      <div class="bg-gray-50 p-4 rounded-lg shadow-md border-l-4 border-yellow-500">
        <div class="flex items-center mb-2">
          <span class="text-2xl mr-2">🤖</span>
          <h2 class="font-semibold text-gray-700">Automatisation</h2>
        </div>
        <p class="text-sm text-gray-600 mb-3">
          Automatisent les actions (scaling, redémarrage).
        </p>
        <div class="text-xs">
          <div class="flex justify-between mb-1">
            <span class="font-medium">Kubernetes HPA</span>
            <span class="text-blue-500">Pull</span>
          </div>
          <div class="flex justify-between mb-1">
            <span class="font-medium">Terraform</span>
            <span class="text-yellow-500">IaC</span>
          </div>
          <div class="flex justify-between">
            <span class="font-medium">Ansible</span>
            <span class="text-yellow-500">Push</span>
          </div>
        </div>
      </div>

      <!-- 7. Synthetic Monitoring -->
      <div class="bg-gray-50 p-4 rounded-lg shadow-md border-l-4 border-teal-500">
        <div class="flex items-center mb-2">
          <span class="text-2xl mr-2">🤖</span>
          <h2 class="font-semibold text-gray-700">Synthetic Monitoring</h2>
        </div>
        <p class="text-sm text-gray-600 mb-3">
          Simule des interactions utilisateurs.
        </p>
        <div class="text-xs">
          <div class="flex justify-between mb-1">
            <span class="font-medium">Datadog Synthetics</span>
            <span class="text-red-500">SaaS</span>
          </div>
          <div class="flex justify-between mb-1">
            <span class="font-medium">New Relic Synthetics</span>
            <span class="text-red-500">SaaS</span>
          </div>
          <div class="flex justify-between">
            <span class="font-medium">Grafana SM</span>
            <span class="text-indigo-500">Open Source</span>
          </div>
        </div>
      </div>

      <!-- 8. Real User Monitoring -->
      <div class="bg-gray-50 p-4 rounded-lg shadow-md border-l-4 border-pink-500">
        <div class="flex items-center mb-2">
          <span class="text-2xl mr-2">📱</span>
          <h2 class="font-semibold text-gray-700">Real User Monitoring</h2>
        </div>
        <p class="text-sm text-gray-600 mb-3">
          Surveille l’expérience réelle des utilisateurs.
        </p>
        <div class="text-xs">
          <div class="flex justify-between mb-1">
            <span class="font-medium">Google Analytics</span>
            <span class="text-red-500">SaaS</span>
          </div>
          <div class="flex justify-between mb-1">
            <span class="font-medium">New Relic Browser</span>
            <span class="text-red-500">SaaS</span>
          </div>
          <div class="flex justify-between">
            <span class="font-medium">Sentry</span>
            <span class="text-red-500">SaaS</span>
          </div>
        </div>
      </div>
    </div>

    <!-- Légende finale -->
    <div class="mt-6 p-4 bg-blue-50 rounded-lg text-center">
      <p class="text-blue-800 font-semibold">
        💡 <strong>Chaque type d'outil répond à un besoin spécifique.</strong>
        <br />
        Combine-les pour une <strong>observabilité complète</strong> !
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MonitoringToolsOverview'
}
</script>
