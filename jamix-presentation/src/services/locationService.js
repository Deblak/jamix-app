import { ref } from 'vue'

export function useLocationService() {
  const cityRef = ref([])
  const departementRef = ref([])

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
      console.error('Erreur fetch communes', e)
      cityRef.value = []
    }
  }
  async function searchDepartementsFr(fragment) {
    if (!fragment || fragment.length < 2) {
      departementRef.value = []
      return
    }
    try {
      const res = await fetch(
        `https://geo.api.gouv.fr/departements?nom=${encodeURIComponent(fragment)}&fields=nom,code&limit=10`
      )
      const data = await res.json()
      departementRef.value = data.map((d) => `${d.nom} (${d.code})`)
    } catch (e) {
      console.error('Erreur fetch départements', e)
      departementRef.value = []
    }
  }

  return { cityRef, searchCityFr, departementRef, searchDepartementsFr }
}
