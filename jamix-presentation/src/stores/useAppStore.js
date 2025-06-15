import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useAppStore = defineStore('app', () => {
  const toastMessage = ref('')
  const screenReaderMessage = ref('')

  function showToast(message, screenReader = '', delay = 8000) {
    toastMessage.value = message
    screenReaderMessage.value = screenReader
    setTimeout(() => {
      toastMessage.value = ''
    }, delay)
  }

  function clearToast() {
    toastMessage.value = ''
    screenReaderMessage.value = ''
  }

  return {
    toastMessage,
    screenReaderMessage,
    showToast,
    clearToast
  }
})
