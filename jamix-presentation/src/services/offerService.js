import axiosApi from '@/services/axiosApi.js'
import { ref } from 'vue'

const offerItems = ref([])
/**
 * Get offers
 */
async function fetchData() {
  try {
    const response = await axiosApi.get('http://localhost:8080/offers')
    offerItems.value = response.data
    console.log(response.data)
  } catch (error) {
    console.error('An error has occured:', error)
  }
}

// async function fetchData() {
//     try {
//         await axiosApi.get('/offers')
//             .then(response => {
//                 offerItems.value = response.data;
//                 console.log(response.data);
//             }).catch(error => {
//                 console.error('A fetch error has occured: ' + error);
//             })
//     } catch (error) {
//         console.error('An unexpected error has occured:', error);
//     }
// }

export { fetchData, offerItems }
