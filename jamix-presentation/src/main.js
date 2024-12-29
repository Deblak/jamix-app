import './assets/styles/less/main.less'
import apiClient from './services/axiosApi.js'
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

function formatDate(dateString) {
  const locale = navigator.language;
  const options = { year: 'numeric', month: 'long', day: 'numeric' };
  const date = new Date(dateString);
  return new Intl.DateTimeFormat(locale, options).format(date);
}

app.config.globalProperties.$axios = apiClient
app.config.globalProperties.$formatDate = formatDate;

app.use(router)
app.use(i18n)

app.mount('#app')
