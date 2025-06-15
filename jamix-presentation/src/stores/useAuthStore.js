import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useAuth = defineStore('auth', () => {
  const token = ref(localStorage.getItem('jwt'))

  function login(userToken) {
    token.value = userToken
    localStorage.setItem('jwt', userToken)
  }

  function logout() {
    token.value = null
    localStorage.removeItem('jwt')
  }

  const isAuthenticated = computed(() => !!token.value)

  return { token, isAuthenticated, login, logout }
})
