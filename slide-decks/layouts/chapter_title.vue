<script setup lang="ts">
import TitleRenderer from '#slidev/title-renderer'
import { onSlideEnter, onSlideLeave, useDarkMode, useIsSlideActive, useNav, useSlideContext } from '@slidev/client'
import Title from '../components/Title.vue'
import SubTitle from '../components/Subtitle.vue'


const { $slidev } = useSlideContext()
const { currentPage, currentLayout, currentSlideRoute } = useNav()
const { isDark } = useDarkMode()
const isActive = useIsSlideActive()

const props = defineProps({
  subject: {
    type: String
  },
  title: {
    type: String,
  },
  logo: {
    type: String,
    default: undefined
  },
  subtitle: {
    type: String,
  },
  target: {
    type: String,
    default: 'IMT 2025-2026'
  },
  class: {
    type: String,
    default: ''
  }
})
</script>

<template>
  <div class="w-full h-full flex flex-col">
    <Progress level="1" position="" opacity="0.1" />
    <header class="basis-1/12">
      <Title><TitleRenderer /></Title>
      <SubTitle>{{ subtitle }}</SubTitle>
      <img v-if="logo" className="max-h-4em fixed top-0 right-0 p-2 z-40" :src="'./'+logo" />
    </header>
    <main class="basis-10/12">
      <div v-if="$slots.left || $slots.right || $slots.top || $slots.bottom" class="">
        <div class="flex flex-col h-full p-1">
          <div v-if="$slots.top" class="overflow-y-auto overflow-x-clip" :class="props.class + ($slots.left || $slots.right ? 'basis-auto' : 'basis-1/2')">
            <slot name="top" />
          </div>
          <div class="flex h-full p-2 gap-2" :class="($slots.top || $slots.bottom ? 'basis-md' : 'basis-1/2')">
            <div v-if="$slots.left"  class="basis-1/2 overflow-y-auto overflow-x-clip">
              <slot name="left" />
            </div>
            <div v-if="$slots.right"  class="basis-1/2 overflow-y-auto overflow-x-clip">
              <slot name="right" />
            </div>
          </div>
          <div v-if="$slots.bottom"  class="overflow-y-auto overflow-x-clip" :class="props.class + ($slots.left || $slots.right ? 'basis-auto' : 'basis-1/2')">
            <slot name="bottom" />
          </div>
        </div>
      </div>
      <div v-else class="h-full overflow-y-auto overflow-x-clip">
        <slot />
      </div>
    </main>
    <footer class="basis-1/12 pb-2 grid grid-cols-3 content-end gap-4 text-align-center">
      <div class="text-xs opacity-60 ">{{ target }}</div>
      <div class="text-xs opacity-60 ">{{ subject }}</div>
      <div class="text-xs py-4opacity-60">
        <SlideCurrentNo class="text-xs opacity-60 " />
      </div>
    </footer>
  </div>
</template>