const BASE_URL = import.meta.env.VITE_API_URL

export const getOfferImageUrl = (filename) => {
  if (!filename) {
    return '/default-offer.jpg'
  }
  return `${BASE_URL}/images/${filename}`
}

export const getPortfolioImageUrl = (filename) => {
  if (!filename) {
    return '/default-portfolio.jpg'
  }
  return `${BASE_URL}/images/${filename}`
}
