<template>
  <div class="m-6 min-h-screen">
    <div class="p-4 bg-gray-900 text-white">
      <h1 class="text-xl md:text-2xl font-bold text-center mb-6 text-red-400">
        Tableau de bord de monitoring
      </h1>

      <div class="flex justify-between mb-4 text-xs text-gray-400">
        <span>Last update: 2026-03-03 14:37:22 (UTC+1)</span>
        <span>Status: ???</span>
        <span>Uptime: 99.9% (ou peut-être 95% ?)</span>
        <span>Alerts: 42 (ou 43 ?)</span>
      </div>

      <div class="space-y-3">
        <div class="flex justify-between p-3 bg-gray-800 rounded-lg border border-gray-700">
          <div class="flex items-center space-x-3">
            <span class="text-yellow-400">⚠️</span>
            <span class="font-mono text-sm">sys-prod-db-03-eu-west</span>
          </div>
          <div class="flex space-x-6">
            <span class="text-orange-300">CPU: 87.3%</span>
            <span class="text-purple-300">Mem: 4.2K</span>
            <span class="text-green-300">Req: 12.7K/s</span>
            <span class="text-red-400">Err: 0.005K</span>
          </div>
        </div>
        <div class="flex justify-between p-3 bg-gray-800 rounded-lg border border-gray-700">
          <div class="flex items-center space-x-3">
            <span class="text-green-400">✅</span>
            <span class="font-mono text-sm">api-gateway-v2-us-east</span>
          </div>
          <div class="flex space-x-6">
            <span class="text-blue-300">Latency: 1200ms</span>
            <span class="text-pink-300">Throughput: 89K</span>
            <span class="text-indigo-300">Conn: 3.1K</span>
            <span class="text-yellow-300">Warn: 17</span>
          </div>
        </div>

        <div class="flex justify-between p-3 bg-gray-800 rounded-lg border border-gray-700">
          <div class="flex items-center space-x-3">
            <span class="text-red-500">🚨</span>
            <span class="font-mono text-sm">cache-redis-cluster-global</span>
          </div>
          <div class="flex space-x-6">
            <span class="text-teal-300">Hit ratio: 0.72K%</span>
            <span class="text-amber-300">Keys: 1.2M (ou 1.2K ?)</span>
            <span class="text-fuchsia-300">Evict: 47/s</span>
            <span class="text-cyan-300">Client: 502</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BadMonitoringExample'
}
</script>
