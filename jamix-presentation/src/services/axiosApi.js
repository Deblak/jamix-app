import axios from 'axios'
import i18n from '@/i18n'

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_URL
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
    if (config.params) {
      Object.keys(config.params).forEach((key) => {
        const val = config.params[key]
        if (val === undefined || val === '') {
          delete config.params[key]
        }
      })
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

/**
 * Response Interceptor to delete the token on session expiration
 */
apiClient.interceptors.response.use(
  (response) => {
    return response
  },
  (error) => {
    if (error.response) {
      if (error.response.status === 401) {
        console.warn('Error 401: unauthenticated')
        localStorage.removeItem('jwt')
        window.location.href = '/login'
      } else {
        const message = error.response.data.message || i18n.global.t('errorUnexpected')
        console.error('Error: ', message)
      }
    } else {
      console.error('Network connection error:', error.message)
    }
    return Promise.reject(error)
  }
)
/**
 * Request Interceptor to delete undefined params in advanced search (error 400)
 */
apiClient.interceptors.request.use(
  (config) => {
    if (config.params) {
      Object.keys(config.params).forEach((key) => {
        const val = config.params[key]
        if (val === undefined || val === '') {
          delete config.params[key]
        }
      })
    }
    return config
  },
  (error) => Promise.reject(error)
)
export default apiClient
