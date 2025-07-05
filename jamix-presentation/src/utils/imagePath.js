const BASE_URL = import.meta.env.VITE_API_URL

export const getImageUrl = (filename) => {
  if (!filename) {
    return '/default-offer.jpg'
  }
  return `${BASE_URL}/images/${filename}`
}
