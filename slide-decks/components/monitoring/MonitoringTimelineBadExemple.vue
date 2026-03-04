<template>
  <div class="flex flex-col gap-8 p-4 m-8 bg-gradient-to-br from-slate-900 to-slate-800 rounded-lg text-white">
    <!-- Developer States -->
    <div class="flex flex-col gap-4">
      <h2 class="text-2xl font-bold">Timothée</h2>
      <h4 class="text-sm">[full-stack developer]</h4>
      <div class="flex gap-6 justify-center flex-wrap">
        <div class="flex flex-col items-center gap-2">
          <div class="w-20 h-20 bg-slate-700 rounded-lg flex items-center justify-center text-4xl">
            {{ developerStates[timeline[timeline.length - 1].devState].emoji }}
          </div>
          <span class="text-sm font-semibold">{{ developerStates[timeline[timeline.length - 1].devState].label }}</span>
        </div>
      </div>
    </div>

    <!-- Timeline -->
    <div class="flex flex-col gap-4">
      <h2 class="text-2xl font-bold">Chronologie du Système</h2>
      <div class="relative">
        <div class="relative flex gap-2 pt-8 flex-wrap justify-center">
          <div v-for="time in timeline" :key="time.hour" class="flex flex-col items-center">
            <div :class="`w-full h-1 rounded ${time.status === 'operational' ? 'bg-green-500' : time.status === 'warning' ? 'bg-yellow-500' : 'bg-red-500'}`"></div>
            <div :class="['w-4 h-4 rounded-full mb-4', time.status === 'operational' ? 'bg-green-500' : time.status === 'warning' ? 'bg-yellow-500' : 'bg-red-500']"></div>
            <span class="text-sm font-mono">{{ time.hour }}</span>
            <span class="text-xs text-slate-300 mt-1">{{ time.emoji }}</span>
          </div>
        </div>
      </div>
    </div>

    <div @click="addNextEvent" class="cursor-pointer">Cliquez ici pour ajouter un événement suivant</div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const developerStates = {
  'happy': { emoji: '🤗', label: 'Content' },
  'développer': { emoji: '💻', label: 'Développement' },
  'déployer': { emoji: '🚀', label: 'Mise en production' },
  'confiant': { emoji: '🫣', label: 'Confiant' },
  'dormir': { emoji: '😪', label: 'Repos' },
  'alerte': { emoji: '⚠️', label: 'Alerté' },
  'exploding': { emoji: '🤯', label: 'Désemparé' },
  'fear': { emoji: '😱', label: 'Peur' },
}

const timeline = ref([
  { hour: '10:00', status: 'operational', emoji: '✅', devState: 'happy' },
])
const next_timeline = ref([
  { hour: '10:10', status: 'operational', emoji: '💻', devState: 'développer' },
  { hour: '14:00', status: 'operational', emoji: '🚀', devState: 'déployer' },
  { hour: '18:00', status: 'operational', emoji: '✅' , devState: 'confiant'},
  { hour: '22:00', status: 'operational', emoji: '✅', devState: 'dormir' },
  { hour: '02:00', status: 'crash', emoji: '💥', devState: 'fear' },
  { hour: '06:00', status: 'warning', emoji: '⚠️', devState: 'exploding' }
])
const addNextEvent = () => {
  if (next_timeline.value.length > 0) {
    timeline.value.push(next_timeline.value.shift())
    console.log(timeline)
  }
}
</script>