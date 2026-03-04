<template>
  <div class="p-4 min-h-screen">
    <!-- Titre -->
    <h1 class="text-3xl font-bold text-gray-800 mb-2 text-center">
      🔄 Pull vs Push : Quelle Stratégie Choisir ?
    </h1>
    <p class="text-center text-gray-600 mb-8 text-sm">
      Comparaison des approches pour la collecte de données
    </p>

    <!-- Tableau comparatif -->
    <div class="overflow-x-auto">
      <table class="min-w-full bg-white rounded-lg shadow-md">
        <thead class="bg-gray-100">
          <tr>
            <th class="p-3 text-left text-sm font-semibold text-gray-700">Critère</th>
            <th class="p-3 text-center text-sm font-semibold text-gray-700">
              <span class="mr-2">🔄</span> Pull-based
            </th>
            <th class="p-3 text-center text-sm font-semibold text-gray-700">
              <span class="mr-2">📤</span> Push-based
            </th>
          </tr>
        </thead>
        <tbody>
          <tr class="border-b border-gray-200">
            <td class="p-3 text-sm">Principe</td>
            <td class="p-3 text-sm">
              Le collecteur <strong>interroge</strong> les cibles (ex: toutes les 15s).
            </td>
            <td class="p-3 text-sm">
              Les cibles <strong>envoient</strong> leurs données au collecteur.
            </td>
          </tr>
          <tr class="border-b border-gray-200 bg-gray-50">
            <td class="p-3 text-sm">Exemples d'outils</td>
            <td class="p-3 text-sm">
              Prometheus, Zabbix, Nagios.
            </td>
            <td class="p-3 text-sm">
              StatsD, Fluentd, Datadog, ELK.
            </td>
          </tr>
          <tr class="border-b border-gray-200">
            <td class="p-3 text-sm">Latence</td>
            <td class="p-3 text-sm">
              Délai = intervalle de scrape (ex: 15s).
              <span class="text-yellow-500">⚠️</span>
            </td>
            <td class="p-3 text-sm">
              Temps réel (dès que la donnée est disponible).
              <span class="text-green-500">✅</span>
            </td>
          </tr>
          <tr class="border-b border-gray-200 bg-gray-50">
            <td class="p-3 text-sm">Cas d'usage idéal</td>
            <td class="p-3 text-sm">
              <ul class="list-disc pl-5 text-xs">
                <li>Métriques système (CPU, mémoire).</li>
                <li>Environnements stables (Kubernetes, serveurs).</li>
                <li>Données périodiques.</li>
              </ul>
            </td>
            <td class="p-3 text-sm">
              <ul class="list-disc pl-5 text-xs">
                <li>Logs, traces, événements.</li>
                <li>Environnements dynamiques (serverless, IoT).</li>
                <li>Données en temps réel.</li>
              </ul>
            </td>
          </tr>
          <tr class="border-b border-gray-200">
            <td class="p-3 text-sm">Avantages</td>
            <td class="p-3 text-sm">
              <ul class="list-disc pl-5 text-xs">
                <li>Contrôle centralisé.</li>
                <li>Simplicité (pas de config côté cible).</li>
                <li>Fiabilité (relance si cible down).</li>
              </ul>
            </td>
            <td class="p-3 text-sm">
              <ul class="list-disc pl-5 text-xs">
                <li>Temps réel.</li>
                <li>Évolutif (adapté au cloud/serverless).</li>
                <li>Flexible (fonctionne derrière NAT/firewall).</li>
              </ul>
            </td>
          </tr>
          <tr class="border-b border-gray-200 bg-gray-50">
            <td class="p-3 text-sm">Inconvénients</td>
            <td class="p-3 text-sm">
              <ul class="list-disc pl-5 text-xs">
                <li>Latence (délai = intervalle de scrape).</li>
                <li>Ressources (beaucoup de connexions si parc large).</li>
                <li>Pas adapté aux données volumineuses (logs).</li>
              </ul>
            </td>
            <td class="p-3 text-sm">
              <ul class="list-disc pl-5 text-xs">
                <li>Complexité (config côté cible).</li>
                <li>Perte de données si collecteur down.</li>
                <li>Sécurité (ouverture de ports entrants).</li>
              </ul>
            </td>
          </tr>
          <tr class="border-b border-gray-200">
            <td class="p-3 text-sm">Exemple d'architecture</td>
            <td class="p-3 text-sm">
              <div class="flex items-center space-x-2">
                <span class="text-blue-500">🔄</span>
                <span class="text-xs">Prometheus → scrape → Pods Kubernetes.</span>
              </div>
            </td>
            <td class="p-3 text-sm">
              <div class="flex items-center space-x-2">
                <span class="text-green-500">📤</span>
                <span class="text-xs">App Node.js → StatsD → Datadog.</span>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PullVsPushComparison'
}
</script>
