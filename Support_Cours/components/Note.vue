<script setup lang="ts">
import { PropType, ref } from 'vue'

type NoteType = "default" | "info" | "warning" | "danger" | "question"

const props = defineProps({
  type: {
    default: "default",
    type: String as PropType<NoteType>
  },
})

const color = (type: NoteType) => {
  switch (type) {
    case "info":
      return "blue-500"
    case "warning":
      return "orange"
    case "danger":
      return "red-500"
    case "question":
      return "blue-800"
    default:
      return "gray-500";
  }
}

</script>

<template>
  <div class="flex mx-2 px-2 border-2 rounded-xl bg-lightgray-500"
    :class="`border-${color(props.type)} text-${color(props.type)}`"
    v-motion
    :initial="{ x: -80 }"
    :enter="{ x: 0 }"
    :leave="{ x: 80 }">
    <svg class="size-6" :class="`stroke-${color(props.type)}`" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24">
      <path v-if="props.type == 'info'" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 11h2v5m-2 0h4m-2.592-8.5h.01M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"/>
      <path v-else-if="props.type == 'warning'" />
      <path v-else-if="props.type == 'danger'" />
      <path v-else-if="props.type == 'question'" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.529 9.988a2.502 2.502 0 1 1 5 .191A2.441 2.441 0 0 1 12 12.582V14m-.01 3.008H12M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"/>
      <path v-else />
    </svg>
    <div>
      <slot />
    </div>
  </div>
</template>
