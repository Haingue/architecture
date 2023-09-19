import './assets/index.css'

import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'

const app = createApp(App)

app.use(router)

window.app = app.mount('#app')
