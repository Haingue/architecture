<template>
  <div class="p-4 bg-gray-50 min-h-screen">
    <!-- Titre -->
    <h1 class="text-2xl md:text-3xl font-bold text-gray-800 mb-2 text-center">
      ☁️ Tableau de bord de monitoring
    </h1>
    <p class="text-center text-gray-600 mb-6 text-sm">
      Statuts en temps réel avec hiérarchie visuelle et contexte
    </p>

    <!-- Légende des icônes météo (pour expliquer la métaphore) -->
<!--    <div class="flex justify-center space-x-6 mb-6">
      <div class="flex items-center space-x-1 text-sm">
        <span class="text-2xl">☀️</span>
        <span>Optimal</span>
      </div>
      <div class="flex items-center space-x-1 text-sm">
        <span class="text-2xl">⛅</span>
        <span>Normal</span>
      </div>
      <div class="flex items-center space-x-1 text-sm">
        <span class="text-2xl">🌧️</span>
        <span>Avertissement</span>
      </div>
      <div class="flex items-center space-x-1 text-sm">
        <span class="text-2xl">⚡</span>
        <span>Critique</span>
      </div>
      <div class="flex items-center space-x-1 text-sm">
        <span class="text-2xl">🌪️</span>
        <span>Urgence</span>
      </div>
    </div>
-->
    <!-- Cartes de monitoring (3 systèmes avec statut clair) -->
    <div class="grid grid-cols-1 md:grid-cols-3 gap-4">

      <!-- Système 1 : Optimal -->
      <div class="bg-white p-4 rounded-lg shadow-md border-l-4 border-green-500">
        <div class="flex justify-between items-start">
          <div>
            <h2 class="font-semibold text-gray-700 flex items-center">
              <span class="mr-2 text-2xl">☀️</span>
              sys-prod-db-03-eu-west
            </h2>
            <p class="text-xs text-gray-500 mt-1">Base de données principale (EU)</p>
          </div>
          <span class="text-xs bg-green-100 text-green-800 px-2 py-1 rounded-full">
            100% disponible
          </span>
        </div>
        <div class="mt-4 space-y-2">
          <div class="flex justify-between">
            <span class="text-sm text-gray-500">CPU</span>
            <span class="font-medium">23%</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-500">Mémoire</span>
            <span class="font-medium">4.2 Go / 16 Go</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-500">Requêtes/s</span>
            <span class="font-medium">12.7K</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-500">Latence</span>
            <span class="font-medium">42 ms</span>
          </div>
        </div>
      </div>

      <!-- Système 2 : Avertissement -->
      <div class="bg-white p-4 rounded-lg shadow-md border-l-4 border-yellow-500">
        <div class="flex justify-between items-start">
          <div>
            <h2 class="font-semibold text-gray-700 flex items-center">
              <span class="mr-2 text-2xl">🌧️</span>
              api-gateway-v2-us-east
            </h2>
            <p class="text-xs text-gray-500 mt-1">Passerelle API (US)</p>
          </div>
          <span class="text-xs bg-yellow-100 text-yellow-800 px-2 py-1 rounded-full">
            Dégradé
          </span>
        </div>
        <div class="mt-4 space-y-2">
          <div class="flex justify-between">
            <span class="text-sm text-gray-500">Latence</span>
            <span class="font-medium text-yellow-600">1.2 s</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-500">Débit</span>
            <span class="font-medium">89K req/s</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-500">Erreurs</span>
            <span class="font-medium text-yellow-600">0.5%</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-500">Connexions</span>
            <span class="font-medium">3.1K</span>
          </div>
        </div>
      </div>

      <!-- Système 3 : Critique -->
      <div class="bg-white p-4 rounded-lg shadow-md border-l-4 border-red-500">
        <div class="flex justify-between items-start">
          <div>
            <h2 class="font-semibold text-gray-700 flex items-center">
              <span class="mr-2 text-2xl">⚡</span>
              cache-redis-cluster-global
            </h2>
            <p class="text-xs text-gray-500 mt-1">Cache distribué</p>
          </div>
          <span class="text-xs bg-red-100 text-red-800 px-2 py-1 rounded-full">
            Incident en cours
          </span>
        </div>
        <div class="mt-4 space-y-2">
          <div class="flex justify-between">
            <span class="text-sm text-gray-500">Taux de hit</span>
            <span class="font-medium text-red-600">52%</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-500">Évictions/s</span>
            <span class="font-medium text-red-600">4.7K</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-500">Clients</span>
            <span class="font-medium">502</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-500">Mémoire</span>
            <span class="font-medium text-red-600">98% utilisée</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'GoodMonitoringExample'
}
</script>
