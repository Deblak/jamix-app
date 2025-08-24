import apiClient from '@/services/axiosApi.js'
import { ref } from 'vue'
const portfolioItems = ref([])
const selectedPortfolio = ref(null)
const portfolioLinkedOffers = ref([])
const userPortfolio = ref({
  id: null,
  imageUrl: '',
  bandName: '',
  tagline: '',
  biography: ''
})

/**
 * Get portfolios for all visitors
 */
async function fetchPortfolios() {
  try {
    const response = await apiClient.get('/portfolios')
    portfolioItems.value = response.data
  } catch (error) {
    console.error('An error has occured:', error)
  }
}

/**
 * Get specific portfolio
 */
async function fetchPortfolioById(id) {
  selectedPortfolio.value = null
  portfolioLinkedOffers.value = []
  const { data } = await apiClient.get(`/portfolios/${id}`)

  selectedPortfolio.value = data.portfolio
  portfolioLinkedOffers.value = data.offers
}

/**
 * Get musician's portfolio
 */
async function fetchUserPortfolio() {
  try {
    const { data } = await apiClient.get('/portfolios/owned')
    userPortfolio.value = data
    return data
  } catch (error) {

      console.error('An error has occurred:', error)
      throw error
  }
}

async function deletePortfolio() {
  try {
    const token = localStorage.getItem('token')
    await apiClient.delete('/portfolios/owned', {
      headers: { Authorization: `Bearer ${token}` }
    })
  } catch (error) {
    console.error('An error has occured:', error)
    throw error
  }
}
/**
 * Search portfolio by band name
 */
async function searchPortfoliosByBandName(bandName) {
  const keyword = bandName.trim()
  if (!keyword) {
    portfolioItems.value = []
    return
  }
  try {
    const { data } = await apiClient.get('/portfolios/search', {
      params: { bandName: keyword }
    })
    portfolioItems.value = data
  } catch (error) {
    console.error('Search error:', error)
    portfolioItems.value = []
  }
}

export {
  fetchPortfolios,
  fetchPortfolioById,
  selectedPortfolio,
  portfolioLinkedOffers,
  fetchUserPortfolio,
  deletePortfolio,
  portfolioItems,
  userPortfolio,
  searchPortfoliosByBandName
}
