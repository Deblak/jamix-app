import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

function parseJwt(token) {
  const payload = token.split('.')[1]
  return JSON.parse(atob(payload))
}

export const useAuth = defineStore('auth', () => {
  // const token = ref(localStorage.getItem('jwt'))
  const token = ref(null)
  if (!token.value && localStorage.getItem('jwt')) {
    token.value = localStorage.getItem('jwt')
  }
  function init() {
    const storedToken = localStorage.getItem('jwt')
    if (storedToken) {
      token.value = storedToken
    }
  }

  function login(userToken) {
    token.value = userToken
    localStorage.setItem('jwt', userToken)
  }

  function logout() {
    token.value = null
    localStorage.removeItem('jwt')
  }

  const isAuthenticated = computed(() => !!token.value)

  const roles = computed(() => {
    if (!token.value) return []
    const payload = parseJwt(token.value)
    return payload?.roles || []
  })

  const username = computed(() => {
    if (!token.value) return null
    const payload = parseJwt(token.value)
    return payload?.username || null
  })

  const isMusician = computed(() => roles.value.includes('ROLE_MUSICIAN'))

  return { token, isAuthenticated, init, login, logout, roles, username, isMusician }
})
