<script setup lang="ts">
import TitleRenderer from '#slidev/title-renderer'
import { onSlideEnter, onSlideLeave, useDarkMode, useIsSlideActive, useNav, useSlideContext } from '@slidev/client'


const { $slidev } = useSlideContext()
const { currentPage, currentLayout, currentSlideRoute } = useNav()
const { isDark } = useDarkMode()
const isActive = useIsSlideActive()

const props = defineProps({
})
</script>

<template>
  <div class="flex h-full">
    <div class="m-auto">
      <slot />
    </div>
  </div>
</template>