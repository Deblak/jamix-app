import axios from 'axios'

axios.defaults.baseURL = 'https://localhost:8080/'

axios.interceptors.request.use(
  (req) => {
    const codeValidate = axios.defaults.validateStatus
    console.log('code validation: ' + codeValidate + 'data: ')
    return req
  },
  (errors) => {
    console.log('Sorry, the request is reject')
    return Promise.reject(errors)
  }
)

axios.interceptors.response.use(
  function (response) {
    console.log('I intercept')
    const filteredResponse = {
      data: response.data,
      status: response.status
    }
    return filteredResponse
  },
  function (error) {
    console.log('Sorry, something get wrong.')
    return Promise.reject(error)
  }
)

export default axios
