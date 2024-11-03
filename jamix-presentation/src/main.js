import './assets/styles/main.less'
import axiosApi from './services/axiosApi.js'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

const app = createApp(App)

app.config.globalProperties.$axios = axiosApi

app.use(router)

app.mount('#app')
