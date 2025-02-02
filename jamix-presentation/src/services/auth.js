import { ref, computed } from 'vue'

export function useAuth() {
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
}