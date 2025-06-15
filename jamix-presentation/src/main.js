import './assets/styles/less/main.less'
import apiClient from './services/axiosApi.js'
import { createApp, reactive } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import i18n, { formatDate } from './i18n'
import { useAuth } from './stores/useAuthStore.js'

const app = createApp(App)
const pinia = createPinia()
app.use(pinia)
const auth = reactive(useAuth())

app.config.globalProperties.$axios = apiClient
app.config.globalProperties.$formatDate = formatDate

app.provide('auth', auth)
app.use(router)
app.use(i18n)

app.mount('#app')
