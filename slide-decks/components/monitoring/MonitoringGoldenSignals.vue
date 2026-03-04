<template>
  <div class="p-6 min-h-screen">
    <h1 class="text-3xl font-bold text-gray-800 mb-8 text-center">
      Golden Signals
    </h1>

    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
      <!-- Carte : Latence -->
      <div
        class="bg-gray-50 p-6 rounded-lg shadow-md border-l-4 border-blue-500 transition-transform hover:scale-105"
      >
        <div class="flex items-center justify-between">
          <h2 class="text-xl font-semibold text-gray-700">Latence</h2>
          <span class="text-2xl">⏱️</span>
        </div>
        <p class="text-gray-600 mt-2">
          Temps de réponse moyen des requêtes (ms).
        </p>
        <div class="mt-4">
          <div class="flex items-end h-24">
            <div
              class="bg-blue-500 h-16 rounded-t-lg"
              :style="{ width: latency + '%' }"
            ></div>
          </div>
          <p class="text-right mt-1 text-sm font-medium">
            {{ latency }} ms
          </p>
        </div>
        <div class="mt-4 p-3 bg-blue-50 rounded-lg">
          <p class="text-blue-700 text-sm">
            <strong>Seuil critique :</strong> > 500 ms
          </p>
        </div>
      </div>

      <!-- Carte : Nombre d'erreurs -->
      <div
        class="bg-gray-50 p-6 rounded-lg shadow-md border-l-4 border-red-500 transition-transform hover:scale-105"
      >
        <div class="flex items-center justify-between">
          <h2 class="text-xl font-semibold text-gray-700">Erreurs</h2>
          <span class="text-2xl">❌</span>
        </div>
        <p class="text-gray-600 mt-2">
          Nombre d'erreurs HTTP sur les 5 dernières minutes.
        </p>
        <div class="mt-4">
          <div class="text-4xl font-bold text-red-600">
            {{ errors }}
          </div>
        </div>
        <div class="mt-4 p-3 bg-red-50 rounded-lg">
          <p class="text-red-700 text-sm">
            <strong>Seuil critique :</strong> > 10 erreurs/min
          </p>
        </div>
      </div>

      <!-- Carte : Trafic -->
      <div
        class="bg-gray-50 p-6 rounded-lg shadow-md border-l-4 border-green-500 transition-transform hover:scale-105"
      >
        <div class="flex items-center justify-between">
          <h2 class="text-xl font-semibold text-gray-700">Trafic</h2>
          <span class="text-2xl">📊</span>
        </div>
        <p class="text-gray-600 mt-2">
          Nombre de requêtes par seconde.
        </p>
        <div class="mt-4">
          <div class="text-4xl font-bold text-green-600">
            {{ traffic }}
          </div>
          <p class="text-sm text-gray-500 mt-1">
            (Pic à {{ peakTraffic }} req/s)
          </p>
        </div>
        <div class="mt-4 p-3 bg-green-50 rounded-lg">
          <p class="text-green-700 text-sm">
            <strong>Seuil critique :</strong> > 1000 req/s
          </p>
        </div>
      </div>

      <!-- Carte : Saturation -->
      <div
        class="bg-gray-50 p-6 rounded-lg shadow-md border-l-4 border-yellow-500 transition-transform hover:scale-105"
      >
        <div class="flex items-center justify-between">
          <h2 class="text-xl font-semibold text-gray-700">Saturation</h2>
          <span class="text-2xl">🔋</span>
        </div>
        <p class="text-gray-600 mt-2">
          Utilisation des ressources (CPU, mémoire, disque).
        </p>
        <div class="mt-4">
          <div class="flex items-center">
            <div class="w-full bg-gray-200 rounded-full h-4">
              <div
                class="bg-yellow-500 h-4 rounded-full"
                :style="{ width: saturation + '%' }"
              ></div>
            </div>
            <span class="ml-2 text-sm font-medium">{{ saturation }}%</span>
          </div>
        </div>
        <div class="mt-4 p-3 bg-yellow-50 rounded-lg">
          <p class="text-yellow-700 text-sm">
            <strong>Seuil critique :</strong> > 80%
          </p>
        </div>
      </div>
    </div>

    <!-- Légende -->
    <div class="mt-8 p-4 bg-gray-100 rounded-lg">
      <h3 class="text-lg font-semibold text-gray-700 mb-2">
        Pourquoi ces 4 signaux ?
      </h3>
      <p class="text-gray-600">
        Les <strong>Golden Signals</strong> (proposés par Google SRE) permettent de mesurer la santé d'une application en temps réel.
        Ils répondent aux questions :
        <strong>Est-ce que ça marche ?</strong> (Erreurs),
        <strong>Est-ce que c'est rapide ?</strong> (Latence),
        <strong>Est-ce que ça tient la charge ?</strong> (Trafic),
        <strong>Est-ce que les ressources sont suffisantes ?</strong> (Saturation).
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "GoldenSignals",
  data() {
    return {
      latency: 350,    // Latence en ms
      errors: 7,       // Nombre d'erreurs
      traffic: 842,    // Trafic actuel (req/s)
      peakTraffic: 950, // Pic de trafic
      saturation: 65,  // Saturation en %
    };
  },
};
</script>
