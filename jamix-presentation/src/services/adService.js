import axiosApi from '@/services/axiosApi.js'
import { ref } from 'vue'

const adItems = ref([])
/**
 * Get ads
 */
async function fetchData() {
  try {
    const response = await axiosApi.get('http://localhost:8080/ads')
    adItems.value = response.data
    console.log(response.data)
  } catch (error) {
    console.error('An error has occured:', error)
  }
}

// async function fetchData() {
//     try {
//         await axiosApi.get('/ads')
//             .then(response => {
//                 adItems.value = response.data;
//                 console.log(response.data);
//             }).catch(error => {
//                 console.error('A fetch error has occured: ' + error);
//             })
//     } catch (error) {
//         console.error('An unexpected error has occured:', error);
//     }
// }

export { fetchData, adItems }
