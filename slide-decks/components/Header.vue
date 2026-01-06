<script setup lang="ts">

const props = defineProps({
  title: {
    default: 'Chapter title',
  }
  subtitle: {
    default: 'Chapter subtitle',
  }
})
</script>

<template>
  <div>
    <h1>{{ title }}</h1>
    <h2>{{ subtitle }}</h2>
  </div>
</template>
