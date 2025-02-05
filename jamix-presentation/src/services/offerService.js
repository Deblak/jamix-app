import apiClient from '@/services/axiosApi.js'
import { ref } from 'vue'

const offerItems = ref([])
/**
 * Get offers for all visitors
 */
async function fetchOffers() {
  try {
    const response = await apiClient.get('http://localhost:8080/offers')
    offerItems.value = response.data
    console.log(response.data)
  } catch (error) {
    console.error('An error has occured:', error)
  }
}

/**
 * Get user's offer(s)
 */
async function fetchUserOffer() {
  try {
    const response = await apiClient.get('http://localhost:8080/offers/my-offer')
    offerItems.value = response.data
  } catch (error) {
    console.error('An error has occured:', error)
  }
}

async function deleteOffer(id) {
  try {
    const token = localStorage.getItem('token')
    await apiClient.delete(`http://localhost:8080/offers/my-offer/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })

    offerItems.value = offerItems.value.filter((offer) => offer.id !== id)
  } catch (error) {
    console.error('An error has occured:', error)
  }
}

export { fetchOffers, fetchUserOffer, deleteOffer, offerItems }
