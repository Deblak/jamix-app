import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080/'

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/',
  headers: {
    'Content-Type': 'application/json'
  }
})

// axios.interceptors.request.use(
//   (req) => {
//     const codeValidate = axios.defaults.validateStatus
//     console.log('code validation: ' + codeValidate + 'data: ')
//     return req
//   },
//   (errors) => {
//     console.log('Sorry, the request is rejected')
//     return Promise.reject(errors)
//   }
// )
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('jwt')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => Promise.reject('Sorry, the request is rejected: ' + error)
)

// axios.interceptors.response.use(
//   function (response) {
//     const filteredResponse = {
//       data: response.data,
//       status: response.status
//     }
//     return filteredResponse
//   },
//   function (error) {
//     console.log('Something get wrong.')
//     return Promise.reject(error)
//   }
// )
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      console.log('Unauthenticated...')
    }
    return Promise.reject('Something get wrong:' + error)
  }
)

export default apiClient
