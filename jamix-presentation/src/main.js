import './assets/styles/main.less'
import axiosApi from './services/axiosApi.js'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createI18n } from 'vue-i18n'
import French from './i18n/locale-fr-FR.json'
import English from './i18n/locale-en-US.json'

const app = createApp(App)

const i18n = createI18n({
  locale: navigator.language,
  fallbackLocale: 'en',
  legacy: false,
  globalInjection: true,
  messages: {
    fr: French,
    en: English
  }
})

app.config.globalProperties.$axios = axiosApi

app.use(router)
app.use(i18n)

app.mount('#app')
