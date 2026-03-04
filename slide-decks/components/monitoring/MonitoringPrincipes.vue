<template>
  <div class="p-4 min-h-screen">
    <h1 class="text-3xl font-bold text-gray-800 mb-8 text-center">
      Les 2 piliers du monitoring : Supervision & Métrologie
    </h1>
    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
      <!-- Carte : Supervision -->
      <div class="bg-gray-50 p-6 rounded-lg shadow-md border-l-4 border-red-500">
        <div class="flex items-center justify-between">
          <h2 class="text-xl font-semibold text-gray-700">Supervision</h2>
          <span class="text-2xl">🚗</span>
        </div>
        <p class="text-gray-600 mt-2">
          « Comme les voyants du tableau de bord de votre voiture… »
        </p>
        <ul class="mt-4 space-y-2">
          <li class="flex items-center">
            <span class="text-red-500 mr-2">🌡️</span>
            <span>Température moteur (CPU).</span>
          </li>
          <li class="flex items-center">
            <span class="text-red-500 mr-2">⛽</span>
            <span>Niveau d’essence (mémoire).</span>
          </li>
          <li class="flex items-center">
            <span class="text-red-500 mr-2">⚠️</span>
            <span>Voyant huile (alerte disque plein).</span>
          </li>
        </ul>
        <div class="mt-4 p-3 bg-red-50 rounded-lg">
          <p class="text-red-700 text-sm">
            <strong>Agir maintenant</strong> : feu rouge, réaction immédiate.
          </p>
        </div>
      </div>

      <!-- Carte : Métrologie -->
      <div class="bg-gray-50 p-6 rounded-lg shadow-md border-l-4 border-blue-500">
        <div class="flex items-center justify-between">
          <h2 class="text-xl font-semibold text-gray-700">Métrologie</h2>
          <span class="text-2xl">📉</span>
        </div>
        <p class="text-gray-600 mt-2">
          « …et le carnet d’entretien pour suivre l’usure. »
        </p>
        <ul class="mt-4 space-y-2">
          <li class="flex items-center">
            <span class="text-blue-500 mr-2">📅</span>
            <span>Kilométrage (requêtes totales).</span>
          </li>
          <li class="flex items-center">
            <span class="text-blue-500 mr-2">🔧</span>
            <span>Historique des pannes (logs).</span>
          </li>
          <li class="flex items-center">
            <span class="text-blue-500 mr-2">📊</span>
            <span>Consommation moyenne (latence).</span>
          </li>
        </ul>
        <div class="mt-4 p-3 bg-blue-50 rounded-lg">
          <p class="text-blue-700 text-sm">
            <strong>Comprendre pour demain</strong> : analyse des causes.
          </p>
        </div>
      </div>
    </div>

    <!-- Légende et exemple -->
    <div class="mt-4 p-4 bg-gray-100 rounded-lg text-center">
      <p class="text-gray-700 font-semibold">
        « Les deux sont indissociables ! »
      </p>
      <div class="mt-2 p-3 bg-gray-200 rounded-lg inline-block">
        <p class="text-gray-800 text-sm">
          <span class="font-bold">Exemple :</span>
          Une alerte CPU à 95% (Supervision) + analyse des pics récurrents à 14h (Métrologie)
          → <span class="text-green-600">Solution : Scaler avant le rush.</span>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MonitoringPillars'
}
</script>
