<template>
  <div class="p-6">
    <!-- Titre -->
    <h1 class="text-3xl font-bold text-gray-800 mb-2 text-center">
      🚀 Stratégie de Monitoring : 9 Étapes Clés
    </h1>
    <p class="text-center text-gray-600 mb-8 text-sm">
      Guide pour déployer un monitoring efficace, de l'instrumentation à l'automatisation.
    </p>

    <div class="h-[60vh] overflow-y-auto bg-white rounded-lg shadow-md p-4">
      <!-- Étapes sous forme de timeline verticale -->
      <div class="relative">
        <!-- Ligne centrale -->
        <div class="absolute left-1/2 transform -translate-x-1/2 h-full w-1 bg-gray-300"></div>

        <!-- Étapes -->
        <div class="space-y-6">
          <!-- Étape 1 -->
          <div class="flex items-center">
            <div class="w-1/2 pr-4 text-right">
              <div class="p-4 bg-gray-50 rounded-lg shadow-md inline-block">
                <h3 class="font-semibold text-gray-700 mb-1">1. Identifier les besoins</h3>
                <p class="text-sm text-gray-600">
                  Quels sont les <strong>SLA</strong> à respecter ?
                  <br />
                  Quels sont les <strong>risques majeurs</strong> ?
                </p>
                <div class="mt-2 text-xs bg-blue-50 p-2 rounded">
                  Exemple : Latence API < 500ms, disponibilité 99.9%.
                </div>
              </div>
            </div>
            <div class="w-1/2 pl-4">
              <div class="w-12 h-12 bg-blue-500 rounded-full flex items-center justify-center text-white font-bold">
                1
              </div>
            </div>
          </div>

          <!-- Étape 2 -->
          <div class="flex items-center">
            <div class="w-1/2 pr-4 text-right">
              <div class="p-4 bg-gray-50 rounded-lg shadow-md inline-block">
                <h3 class="font-semibold text-gray-700 mb-1">2. Choisir les outils</h3>
                <p class="text-sm text-gray-600">
                  <strong>Open Source</strong> (Prometheus, ELK) vs <strong>SaaS</strong> (Datadog).
                  <br />
                  <strong>Pull</strong> vs <strong>Push</strong>.
                </p>
                <div class="mt-2 text-xs bg-green-50 p-2 rounded">
                  Exemple : Prometheus + Grafana pour les métriques.
                </div>
              </div>
            </div>
            <div class="w-1/2 pl-4">
              <div class="w-12 h-12 bg-green-500 rounded-full flex items-center justify-center text-white font-bold">
                2
              </div>
            </div>
          </div>

          <!-- Étape 3 -->
          <div class="flex items-center">
            <div class="w-1/2 pr-4 text-right">
              <div class="p-4 bg-gray-50 rounded-lg shadow-md inline-block">
                <h3 class="font-semibold text-gray-700 mb-1">3. Instrumenter le code</h3>
                <p class="text-sm text-gray-600">
                  Ajouter des <strong>métriques</strong> (Prometheus client).
                  <br />
                  Configurer les <strong>logs</strong> (Fluentd).
                </p>
                <div class="mt-2 text-xs bg-purple-50 p-2 rounded">
                  Exemple : Endpoint <code>/metrics</code> pour Prometheus.
                </div>
              </div>
            </div>
            <div class="w-1/2 pl-4">
              <div class="w-12 h-12 bg-purple-500 rounded-full flex items-center justify-center text-white font-bold">
                3
              </div>
            </div>
          </div>

          <!-- Étape 4 -->
          <div class="flex items-center">
            <div class="w-1/2 pr-4 text-right">
              <div class="p-4 bg-gray-50 rounded-lg shadow-md inline-block">
                <h3 class="font-semibold text-gray-700 mb-1">4. Centraliser les données</h3>
                <p class="text-sm text-gray-600">
                  Agrégation avec <strong>Prometheus</strong> (métriques) ou <strong>Loki</strong> (logs).
                  <br />
                  Stockage (ex: rétention 30j pour les logs).
                </p>
                <div class="mt-2 text-xs bg-yellow-50 p-2 rounded">
                  Exemple : Fluentd → ELK pour les logs.
                </div>
              </div>
            </div>
            <div class="w-1/2 pl-4">
              <div class="w-12 h-12 bg-yellow-500 rounded-full flex items-center justify-center text-white font-bold">
                4
              </div>
            </div>
          </div>

          <!-- Étape 5 -->
          <div class="flex items-center">
            <div class="w-1/2 pr-4 text-right">
              <div class="p-4 bg-gray-50 rounded-lg shadow-md inline-block">
                <h3 class="font-semibold text-gray-700 mb-1">5. Créer des dashboards</h3>
                <p class="text-sm text-gray-600">
                  Dans <strong>Grafana</strong> ou <strong>Kibana</strong> :
                  <br />
                  - Latence, taux d’erreur, CPU.
                </p>
                <div class="mt-2 text-xs bg-red-50 p-2 rounded">
                  Exemple : Dashboard "API Overview" avec Grafana.
                </div>
              </div>
            </div>
            <div class="w-1/2 pl-4">
              <div class="w-12 h-12 bg-red-500 rounded-full flex items-center justify-center text-white font-bold">
                5
              </div>
            </div>
          </div>

          <!-- Étape 6 -->
          <div class="flex items-center">
            <div class="w-1/2 pr-4 text-right">
              <div class="p-4 bg-gray-50 rounded-lg shadow-md inline-block">
                <h3 class="font-semibold text-gray-700 mb-1">6. Configurer les alertes</h3>
                <p class="text-sm text-gray-600">
                  Dans <strong>Alertmanager</strong> ou <strong>Datadog</strong> :
                  <br />
                  - Seuil : latence > 1s pendant 5min.
                </p>
                <div class="mt-2 text-xs bg-indigo-50 p-2 rounded">
                  Exemple : Alerte Slack si CPU > 90%.
                </div>
              </div>
            </div>
            <div class="w-1/2 pl-4">
              <div class="w-12 h-12 bg-indigo-500 rounded-full flex items-center justify-center text-white font-bold">
                6
              </div>
            </div>
          </div>

          <!-- Étape 7 -->
          <div class="flex items-center">
            <div class="w-1/2 pr-4 text-right">
              <div class="p-4 bg-gray-50 rounded-lg shadow-md inline-block">
                <h3 class="font-semibold text-gray-700 mb-1">7. Automatiser les réactions</h3>
                <p class="text-sm text-gray-600">
                  <strong>Scaling</strong> (Kubernetes HPA),
                  <br />
                  <strong>Remédiation</strong> (Ansible).
                </p>
                <div class="mt-2 text-xs bg-teal-50 p-2 rounded">
                  Exemple : HPA si CPU > 70%.
                </div>
              </div>
            </div>
            <div class="w-1/2 pl-4">
              <div class="w-12 h-12 bg-teal-500 rounded-full flex items-center justify-center text-white font-bold">
                7
              </div>
            </div>
          </div>

          <!-- Étape 8 -->
          <div class="flex items-center">
            <div class="w-1/2 pr-4 text-right">
              <div class="p-4 bg-gray-50 rounded-lg shadow-md inline-block">
                <h3 class="font-semibold text-gray-700 mb-1">8. Former les équipes</h3>
                <p class="text-sm text-gray-600">
                  Expliquer comment :
                  <br />
                  - Lire les dashboards.
                  - Réagir aux alertes.
                </p>
                <div class="mt-2 text-xs bg-pink-50 p-2 rounded">
                  Exemple : Session "Monitoring 101" pour les devs.
                </div>
              </div>
            </div>
            <div class="w-1/2 pl-4">
              <div class="w-12 h-12 bg-pink-500 rounded-full flex items-center justify-center text-white font-bold">
                8
              </div>
            </div>
          </div>

          <!-- Étape 9 -->
          <div class="flex items-center">
            <div class="w-1/2 pr-4 text-right">
              <div class="p-4 bg-gray-50 rounded-lg shadow-md inline-block">
                <h3 class="font-semibold text-gray-700 mb-1">9. Améliorer en continu</h3>
                <p class="text-sm text-gray-600">
                  - Ajouter de nouvelles métriques.
                  - Ajuster les seuils d’alerte.
                  <br />
                  - Supprimer les métriques inutiles.
                </p>
                <div class="mt-2 text-xs bg-gray-100 p-2 rounded">
                  Exemple : Revue mensuelle des dashboards.
                </div>
              </div>
            </div>
            <div class="w-1/2 pl-4">
              <div class="w-12 h-12 bg-gray-500 rounded-full flex items-center justify-center text-white font-bold">
                9
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Résumé visuel -->
      <div class="mt-8 p-4 bg-blue-50 rounded-lg text-center">
        <h2 class="font-semibold text-blue-800 mb-2">
          🎯 Résumé : Une Stratégie de Monitoring Efficace
        </h2>
        <p class="text-sm text-gray-700">
          <strong>Commence petit</strong> (métriques critiques) →
          <strong>Étends</strong> (logs, traces) →
          <strong>Automatise</strong> (alertes, scaling) →
          <strong>Améliore</strong> en continu.
        </p>
        <div class="mt-3 flex justify-center space-x-4">
          <div class="flex items-center space-x-1 text-xs">
            <span class="text-blue-500">📊</span>
            <span>Métriques</span>
          </div>
          <div class="flex items-center space-x-1 text-xs">
            <span class="text-green-500">📜</span>
            <span>Logs</span>
          </div>
          <div class="flex items-center space-x-1 text-xs">
            <span class="text-purple-500">🔍</span>
            <span>Traces</span>
          </div>
          <div class="flex items-center space-x-1 text-xs">
            <span class="text-red-500">🚨</span>
            <span>Alertes</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MonitoringStrategySteps'
}
</script>
