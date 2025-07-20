import './assets/styles/style.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import apiClient from './services/axiosApi.js'
import i18n, { formatDate } from './i18n'
import App from './App.vue'
import router from './router'
import { useAuth } from '@/stores/useAuthStore'

const app = createApp(App)
const pinia = createPinia()
app.use(pinia)

const authStore = useAuth()
authStore.init()

app.config.globalProperties.$axios = apiClient
app.config.globalProperties.$formatDate = formatDate

app.use(router)
app.use(i18n)

app.mount('#app')
