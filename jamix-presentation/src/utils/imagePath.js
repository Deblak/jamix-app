const BASE_URL = import.meta.env.VITE_IMAGE_URL

export const getOfferImageUrl = (filename) => {
  if (!filename) {
    return '/default-offer.jpg'
  }
  return `${BASE_URL}/${filename}`
}

export const getPortfolioImageUrl = (filename) => {
  if (!filename) {
    return '/default-portfolio.jpg'
  }
  return `${BASE_URL}/${filename}`
}

export const getMusicImageUrl = (filename) => {
  if (!filename) {
    return '/default-music.jpg'
  }
  return `${BASE_URL}/${filename}`
}
