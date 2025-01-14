import axios from 'axios'

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
        alert('Your session has expired. Please log in again.')
        localStorage.removeItem('jwt')
        window.location.href = '/login'
      } else {
        console.error('Error: ', error.response.data)
        alert(`Error: ${error.response.data.message || 'An error has occurred.'}`)
      }
    } else {
      console.error('Network connection error:', error.message)
      alert('Unable to reach the server. Check your network connection.')
    }
    return Promise.reject(error)
  }
)

export default apiClient
