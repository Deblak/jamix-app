import { createI18n } from 'vue-i18n'
import French from './locale-fr-FR.json'
import English from './locale-en-US.json'

export const i18n = createI18n({
  locale: navigator.language,
  fallbackLocale: 'en',
  legacy: false,
  globalInjection: true,
  messages: {
    fr: French,
    en: English
  }
})

export function formatDate(dateString) {
  const locale = navigator.language
  const options = { year: 'numeric', month: 'long', day: 'numeric' }
  const date = new Date(dateString)
  return new Intl.DateTimeFormat(locale, options).format(date)
}

export default i18n
