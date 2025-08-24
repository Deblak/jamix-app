<script setup>
import { ref, onMounted } from 'vue'
import { portfolioItems, searchPortfoliosByBandName, fetchPortfolios } from '@/services/portfolioService.js'
import { getPortfolioImageUrl } from '@/utils/imagePath'
const query = ref('');
const loading = ref(false);
const errorMsg = ref('');

onMounted(async () => {
    loading.value = true
    try {
        await fetchPortfolios()
    } catch {
        errorMsg.value = 'Error'
    } finally {
        loading.value = false
    }
})
async function doSearch() {
    errorMsg.value = ''
    loading.value = true
    try {
        await searchPortfoliosByBandName(query.value)
    } catch (e) {
        errorMsg.value = 'Error'
    } finally {
        loading.value = false
    }
}

function onKey(e) {
    if (e.key === 'Enter') doSearch()
}
</script>

<template>
    <main class="container-custom">
        <section class="mx-0 mb-4">
            <div class="input-group">
                <input v-model="query" @keydown="onKey" type="text" class="form-control"
                    :placeholder="$t('searchPortfolioPlaceholder')" />
                <button class="btn btn-primary" :disabled="loading" @click="doSearch">
                    <span v-if="!loading">{{ $t('search') }}</span>
                    <span v-else>...</span>
                </button>
            </div>
            <p v-if="errorMsg" class="text-alert mt-2">{{ errorMsg }}</p>
        </section>

        <section v-if="!loading && portfolioItems.length === 0 && query">
            <p>{{ $t('noResults') }}</p>
        </section>

        <section class="row g-4">
            <div class="col-12 col-md-6 col-lg-4 col-xl-3" v-for="portfolio in portfolioItems" :key="portfolio.id">
                <router-link :to="`/portfolios/${portfolio.id}`"
                    class="card h-100 text-reset text-decoration-none hover-shadow">
                    <img class="card-img-top" :src="getPortfolioImageUrl(portfolio.imageUrl)"
                        :alt="portfolio.bandName" />
                    <div class="card-body">
                        <h5 class="card-title">{{ portfolio.bandName }}</h5>
                        <p class="card-text" v-if="portfolio.biography">{{ portfolio.biography }}</p>
                        <span class="stretched-link"></span>
                    </div>
                </router-link>
            </div>
        </section>
    </main>
</template>

<style scoped>
.card-img-top {
    object-fit: cover;
    height: 180px;
}

.card {
    cursor: pointer;
}
</style>