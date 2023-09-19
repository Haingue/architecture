<script>
import { RouterView } from 'vue-router'
import HeaderComponents from '@/components/Header.vue'
import FooterComponents from '@/components/Footer.vue'

export default {
  components: { HeaderComponents, RouterView, FooterComponents }
}
</script>

<template>
  <HeaderComponents />
  <RouterView />
  <FooterComponents />
</template>
