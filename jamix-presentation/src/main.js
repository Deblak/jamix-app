import './assets/styles/main.less'
//import { AxiosResponse, AxiosError } from 'axios'
import axios from 'axios'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

axios.defaults.baseURL = 'https://localhost:8080/'

axios.interceptors.request.use(
  (req) => {
    //let codeValidate = req.validateStatus;
    const codeValidate = axios.defaults.validateStatus
    console.log('code validation: ' + codeValidate + 'data: ')
    return req
  },
  (errors) => {
    console.log('Sorry, I reject the request')
    return Promise.reject(errors)
  }
)

// interface FilteredResponse {
//   data: any
//   status: number
// }

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
    console.log('Nope, I reject')
    return Promise.reject(error)
  }
)

// axios.interceptors.response.use(
//   (response: AxiosResponse) => {
//     console.log('I intercept')

//     const { data, status } = response
//     console.log('Filtered data:', data)
//     console.log('Status code:', status)

//     return response
//   },
//   (error: AxiosError) => {
//     console.log('Sorry, I reject the response')
//     return Promise.reject(error)
//   }
// )

const app = createApp(App)

app.config.globalProperties.$axios = axios

app.use(router)

app.mount('#app')
