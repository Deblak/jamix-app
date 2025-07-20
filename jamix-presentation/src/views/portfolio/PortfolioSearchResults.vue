<script setup>
import { ref, onMounted, watch } from 'vue'
import DOMPurify from 'dompurify'
import { useRoute } from 'vue-router'
import OfferCard from '@/components/OfferCard.vue'
import SelectPreference from '@/components/SelectPreference.vue'
import apiClient from '@/services/axiosApi.js'
import { useLocationService } from '@/services/locationService.js'
const { cityRef, searchCityFr } = useLocationService()
const { zipCodeRef, searchZipCodeFr } = useLocationService()
function purifyInput(input) {
    return DOMPurify.sanitize(input)
}
const offerList = ref([])
const route = useRoute()
// filters values
const filterKeyword = ref(route.query.keyword || '')
const filterInstrumentId = ref(route.query.instrumentId || '')
const filterStyleId = ref(route.query.styleId || '')
const filterGoalId = ref(route.query.goalId || '')
const filterSortOrder = ref(route.query.sort || '')
//dropdowns
const instrumentOptions = ref([])
const styleOptions = ref([])
const goalOptions = ref([])
const filterCity = ref('')
const filterZipCode = ref('')

async function loadFilterOptions() {
    try {
        const [instRes, styleRes, goalRes] = await Promise.all([
            apiClient.get('/api/instruments'),
            apiClient.get('/api/styles'),
            apiClient.get('/api/goals')
        ])
        instrumentOptions.value = instRes.data
        styleOptions.value = styleRes.data
        goalOptions.value = goalRes.data
    } catch (error) {
        console.error('Error loading filter options', error)
    }
}

async function performSearch() {
    const cleanKeyword = purifyInput(filterKeyword.value.trim())
    if (
        !cleanKeyword &&
        !filterInstrumentId.value &&
        !filterStyleId.value &&
        !filterGoalId.value
    ) {
        const response = await apiClient.get('/offers')
        offerList.value = response.data
        applyClientSideSort()
        return
    }

    const params = {}
    if (cleanKeyword) params.title = cleanKeyword
    if (filterInstrumentId.value) params.instrumentId = filterInstrumentId.value
    if (filterStyleId.value) params.styleId = filterStyleId.value
    if (filterGoalId.value) params.goalId = filterGoalId.value
    if (filterZipCode.value) {
        params.zipCode = filterZipCode.value
    }
    try {
        const response = await apiClient.get('/offers', { params })
        offerList.value = response.data
        applyClientSideSort()
    } catch (error) {
        console.error('Error fetching offers', error)
        offerList.value = []
    }
}
// orders
function applyClientSideSort() {
    if (!filterSortOrder.value) return
    offerList.value.sort((a, b) => {
        const timeA = new Date(a.createdAt).getTime()
        const timeB = new Date(b.createdAt).getTime()
        return filterSortOrder.value === 'dateAsc' ? timeA - timeB : timeB - timeA
    })
}
function setSortOrder(order) {
    filterSortOrder.value = order
    applyClientSideSort()
}
function onInstrumentChange(newVal) {
    filterInstrumentId.value =
        filterInstrumentId.value === newVal ? '' : newVal
}
function onStyleChange(newVal) {
    filterStyleId.value =
        filterStyleId.value === newVal ? '' : newVal
}
function onGoalChange(newVal) {
    filterGoalId.value =
        filterGoalId.value === newVal ? '' : newVal
}

watch(filterInstrumentId, () => {
    performSearch()
})
watch(filterStyleId, () => {
    performSearch()
})
watch(filterGoalId, () => {
    performSearch()
})

onMounted(async () => {
    await loadFilterOptions()
    await performSearch()
})
watch(
    () => route.query.keyword,
    newKeyword => {
        filterKeyword.value = newKeyword
        performSearch()
    }
)
</script>

<template>
    <main class="container">
        <!-- Research with filters -->
        <section class="row g-3 mb-4">
            <div class="input-group">
                <input v-model="filterKeyword" @keyup.enter="performSearch" type="text" class="form-control"
                    :placeholder="$t('searchPortfolio')" />
                <button class="btn btn-primary" @click="performSearch">
                    {{ $t('search') }}
                </button>
            </div>
            <details>
                <summary class="btn btn-warning mb-3">
                    <i class="bi bi-filter-left"></i>
                    {{ $t('filter') }}
                </summary>
                <div class="rounded-3 bg-light-subtle d-flex flex-wrap justify-content-around pt-3">
                    <div class="col-auto mb-3">
                        <label for="city" class="form-label fw-medium">{{ $t('city') }}</label>
                        <input id="city" list="communes" v-model="filterCity" @input="searchCityFr(filterCity)"
                            type="text" class="form-control col-8" :placeholder="$t('city')" />
                        <datalist id="communes">
                            <option v-for="city in cityRef" :key="city" :value="city" />
                        </datalist>
                    </div>
                    <div class="col-auto">
                        <label for="zipCode" class="form-label fw-medium">{{ $t('zipCode') }}</label>
                        <input id="zipCode" list="zipList" v-model="filterZipCode"
                            @input="searchZipCodeFr(filterZipCode)" type="text" class="form-control"
                            :placeholder="$t('zipCode')" />
                        <datalist id="zipList">
                            <option v-for="zip in zipCodeRef" :key="zip" :value="zip" />
                        </datalist>
                    </div>
                    <SelectPreference id="instrumentId" :label="$t('instrument')" :options="instrumentOptions"
                        :modelValue="filterInstrumentId" @update:modelValue="onInstrumentChange" />
                    <SelectPreference id="styleId" :label="$t('style')" :options="styleOptions"
                        :modelValue="filterStyleId" @update:modelValue="onStyleChange" />
                    <SelectPreference id="goalId" :label="$t('goal')" :options="goalOptions" :modelValue="filterGoalId"
                        @update:modelValue="onGoalChange" />

                </div>
            </details>
        </section>
        <!-- Sort -->
        <div class="d-flex align-items-center gap-2 mb-4">
            <span class="fw-semibold">{{ $t('sort') }}</span>
            <button type="button" class="btn btn-outline-primary btn-sm"
                :class="{ active: filterSortOrder.value === 'dateDesc' }" @click="setSortOrder('dateDesc')">
                <i class="bi bi-arrow-down"></i> {{ $t('newest') }}
            </button>
            <button type="button" class="btn btn-outline-primary btn-sm"
                :class="{ active: filterSortOrder.value === 'dateAsc' }" @click="setSortOrder('dateAsc')">
                <i class="bi bi-arrow-up"></i> {{ $t('oldest') }}
            </button>


        </div>

        <!-- Results -->
        <h2 class="title-1">{{ $t('portfolio') }}</h2>
        <section class="row g-5">
            <div class="col-12 col-md-6 col-lg-4" v-for="offer in offerList" :key="offer.id">
                <OfferCard :id="offer.id" :title="offer.title" :description="offer.description" :city="offer.city"
                    :zipCode="offer.zipCode" :createdAt="offer.createdAt" :instrument="offer.instrumentName"
                    :style="offer.styleName" :goal="offer.goalType" />
            </div>
        </section>
    </main>
</template>
<style>
summary {
    width: fit-content;
    list-style: none;
    appearance: none;
    -webkit-appearance: none;
}

summary::marker {
    content: '';
}
</style>