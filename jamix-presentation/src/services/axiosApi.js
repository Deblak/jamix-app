import axios from 'axios'
import { i18n } from '../main'

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/',
  headers: {
    'Content-Type': 'application/json'
  }
})

/**
 * Request Interceptor to retrieve the token in the localStorage
 * and add it in headers.
 */
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('jwt')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    console.error('Error in request interceptor:', error)
    return Promise.reject(error)
  }
)

/**
 * Response Interceptor to delete the token on session expiration (error 401)
 */
apiClient.interceptors.response.use(
  (response) => {
    return response
  },
  (error) => {
    if (error.response) {
      if (error.response.status === 401) {
        console.warn('Error 401: unauthenticated')
        alert(t('errorSession'))
        localStorage.removeItem('jwt')
        window.location.href = '/login'
      } else {
        const message = error.response.data.message || i18n.global.t('errorUnexpected')
        console.error('Error: ', message)
        alert(i18n.global.t('errorUnexpected') + ': ' + message)
      }
    } else {
      console.error('Network connection error:', error.message)
      alert(i18n.global.t('errorReachServor'))
    }
    return Promise.reject(error)
  }
)

export default apiClient
