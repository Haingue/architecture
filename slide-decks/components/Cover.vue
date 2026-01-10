<script setup lang="ts">
import SubTitle from './Subtitle.vue';
import Title from './Title.vue';
import { useSlideContext } from '@slidev/client';

const props = defineProps({
  logoUrl: {
    type: String
  },
  title: {
    default: 'Chapter title',
  },
  subject: {
    default: 'Architecture & Réalisation WEB',
  },
  years: {
    default: `${new Date().getUTCFullYear()}-${new Date().getUTCFullYear()+1}`,
  },
})

</script>

<template>
  <div class="grid grid-cols-3 grid-flow-col gap-2 text-center">
    <div class="row-span-0">
      <img src="/images/IMT-logo.png"/>
    </div>
    <div class="row-span-2 row-start-3">
      <img src="/images/cc.svg"/>
    </div>
    <div class="row-span-3 col-span-3">
      <div class="grid grid-cols-1 gap-2 h-full">
        <img v-if="logoUrl" :src="'./'+logoUrl" class="m-auto max-h-120px" />
        <Title>{{ title }}</Title>
        <SubTitle>{{ subject }}</SubTitle>
        <h2>{{ years }}</h2>
      </div>
    </div> 
    <div class="row-span-1 row-start-3">
      <h3>Fabien HAINGUE</h3>
    </div>
  </div>
</template>
