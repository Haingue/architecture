<template>
  <div class="p-4 min-h-screen">
    <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-6">
      <div class="bg-gray-50 p-4 rounded-lg shadow-md">
        <h2 class="text-lg md:text-xl font-semibold text-gray-700 mb-3 flex items-center">
          <span class="mr-2">🔄</span> Pratiques DevOps liées au monitoring
        </h2>
        <ul class="space-y-2">
          <li class="flex items-start pl-4 border-l-2 border-blue-500">
            <span class="text-blue-500 mr-2 mt-1">🤝</span>
            <div>
              <strong>Collaboration Dev/Ops</strong>
              <p class="text-sm text-gray-600">
                Les devs voient l’impact de leur code en production, les ops comprennent les besoins métiers.
              </p>
            </div>
          </li>
          <li class="flex items-start pl-4 border-l-2 border-blue-500">
            <span class="text-blue-500 mr-2 mt-1">⚡</span>
            <div>
              <strong>Automatisation des réactions</strong>
              <p class="text-sm text-gray-600">
                Exemple : Si la latence > 500ms, scaler automatiquement.
              </p>
            </div>
          </li>
          <li class="flex items-start pl-4 border-l-2 border-blue-500">
            <span class="text-blue-500 mr-2 mt-1">📈</span>
            <div>
              <strong>Amélioration continue</strong>
              <p class="text-sm text-gray-600">
                Après un incident, ajouter une métrique manquante.
              </p>
            </div>
          </li>
        </ul>
      </div>

      <div class="bg-gray-50 p-4 rounded-lg shadow-md">
        <h2 class="text-lg md:text-xl font-semibold text-gray-700 mb-3 flex items-center">
          <span class="mr-2">🛠️</span> Outils & Exemples
        </h2>
        <ul class="space-y-2">
          <li class="flex items-center pl-4 border-l-2 border-orange-500">
            <span class="text-orange-500 mr-2">📊</span>
            <span><strong>Prometheus + Grafana</strong> : Alertes en temps réel sur les SLO.</span>
          </li>
          <li class="flex items-center pl-4 border-l-2 border-orange-500">
            <span class="text-orange-500 mr-2">📦</span>
            <span><strong>ELK Stack</strong> : Centralisation des logs pour le debugging.</span>
          </li>
          <li class="flex items-center pl-4 border-l-2 border-orange-500">
            <span class="text-orange-500 mr-2">🚀</span>
            <span><strong>Feature Flags</strong> : Déployer progressivement et monitorer l’impact.</span>
          </li>
          <li class="flex items-center pl-4 border-l-2 border-orange-500">
            <span class="text-orange-500 mr-2">🤖</span>
            <span><strong>Kubernetes + HPA</strong> : Scaling automatique basé sur la charge.</span>
          </li>
        </ul>
      </div>
    </div>

    <div class="bg-gray-100 p-4 rounded-lg shadow-inner mb-6">
      <h2 class="text-lg md:text-xl font-semibold text-gray-700 mb-2 flex items-center">
        <span class="mr-2">🎬</span> Cas réel : NetFlix et le "Chaos Monkey"
      </h2>
      <ul class="list-disc pl-5 space-y-1 text-gray-600">
        <li>Simuler des pannes (Chaos Engineering) pour tester la résilience.</li>
        <li>Automatiser les réactions (ex : rediriger le trafic si un service tombe).</li>
        <li>Améliorer en continu grâce aux données collectées.</li>
      </ul>
      <p class="mt-2 font-semibold text-gray-800">
        Résultat : 99.99% de disponibilité malgré des milliers de déploiements par jour.
      </p>
    </div>

    <div class="p-3 bg-blue-50 rounded-lg text-center">
      <p class="text-blue-800 font-semibold text-sm md:text-base">
        Le monitoring DevOps, c’est :
        <span class="text-blue-600">🔍 Visibilité</span> +
        <span class="text-blue-600">🤝 Responsabilité partagée</span> +
        <span class="text-blue-600">⚡ Réaction automatique</span> +
        <span class="text-blue-600">📈 Apprentissage continu</span>
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MonitoringMethodology',
}
</script>

