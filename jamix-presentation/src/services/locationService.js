import { ref } from 'vue'

export function useLocationService() {
  const cityRef = ref([])
  //const departementRef = ref([])

  /**
   * Thanks to geo.api.gouv.fr
   * @param {string} fragment —
   */
  async function searchCityFr(fragment) {
    if (!fragment || fragment.length < 3) {
      cityRef.value = []
      return
    }
    try {
      const res = await fetch(
        `https://geo.api.gouv.fr/communes?nom=${encodeURIComponent(fragment)}&fields=nom,codesPostaux&limit=10`
      )
      const data = await res.json()
      cityRef.value = data.flatMap((commune) =>
        commune.codesPostaux.map((cp) => `${commune.nom} (${cp})`)
      )
    } catch (e) {
      console.error('Error fetch city', e)
      cityRef.value = []
    }
  }
  // async function searchDepartementsFr(fragment) {
  //   if (!fragment || fragment.length < 2) {
  //     departementRef.value = []
  //     return
  //   }
  //   try {
  //     const res = await fetch(
  //       `https://geo.api.gouv.fr/departements?nom=${encodeURIComponent(fragment)}&fields=nom,code&limit=10`
  //     )
  //     const data = await res.json()
  //     departementRef.value = data.map((d) => `${d.nom} (${d.code})`)
  //   } catch (e) {
  //     console.error('Err fetch départements', e)
  //     departementRef.value = []
  //   }
  // }
  async function isCityAndZipValid(city, zipCode) {
    try {
      const res = await fetch(`https://geo.api.gouv.fr/communes?codePostal=${zipCode}&fields=nom`)
      const data = await res.json()

      if (!Array.isArray(data) || data.length === 0) return false

      return data.some((commune) => commune.nom.trim().toLowerCase() === city.trim().toLowerCase())
    } catch (e) {
      console.error('Validation error city / zip code :', e)
      return false
    }
  }

  return { cityRef, searchCityFr, isCityAndZipValid }
}
