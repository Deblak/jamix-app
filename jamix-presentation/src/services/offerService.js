import apiClient from '@/services/axiosApi.js'
import { ref } from 'vue'

const offerItems = ref([])
const selectedOffer = ref(null)
/**
 * Get offers for all visitors
 */
async function fetchOffers() {
  try {
    const response = await apiClient.get('/offers')
    offerItems.value = response.data
  } catch (error) {
    console.error('An error has occured:', error)
  }
}
async function fetchOfferById(id) {
  try {
    const response = await apiClient.get(`/offers/${id}`)
    selectedOffer.value = response.data
  } catch (error) {
    console.error('An error has occured:', error)
  }
}

/**
 * Get musician's offer(s)
 */
async function fetchUserOffer() {
  try {
    const response = await apiClient.get('/offers/owned')
    offerItems.value = response.data
  } catch (error) {
    console.error('An error has occured:', error)
  }
}

async function deleteOffer(id) {
  try {
    const token = localStorage.getItem('token')
    await apiClient.delete(`/offers/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })

    offerItems.value = offerItems.value.filter((offer) => offer.id !== id)
  } catch (error) {
    console.error('An error has occured:', error)
  }
}

export { fetchOffers, fetchOfferById, selectedOffer, fetchUserOffer, deleteOffer, offerItems }
