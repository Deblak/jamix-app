<script setup>
import { ref, computed, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import { fetchUserPortfolio, userPortfolio, deletePortfolio } from '@/services/portfolioService.js';
import { fetchUserOffer, offerItems } from '@/services/offerService.js';
import MusicCard from '@/components/MusicCard.vue';
import OfferCard from '@/components/OfferCard.vue';
import { getPortfolioImageUrl } from '@/utils/imagePath';
import { useAppStore } from '@/stores/useAppStore';
import { useSwalFire } from '@/composables/useSwalFire';
import { useRouter } from 'vue-router';

const router = useRouter();

const store = useAppStore();
const { swalConfirm, swalError } = useSwalFire();
const { t } = useI18n();

const portfolio = userPortfolio;
const errorMsg = ref('');
const imagePath = computed(() => getPortfolioImageUrl(portfolio.value.imageUrl));
const offers = offerItems;

onMounted(async () => {
    try {
        await fetchUserPortfolio();
    } catch (error) {
        if (error.response.status === 404) {
            router.push({ name: 'portfolioCreate' })
        }
        errorMsg.value = error.message || t('error.loadingPortfolio');
    }
    fetchUserOffer();
});
async function handleDelete() {
    const result = await swalConfirm(t('confirmPortfolioDelete'));
    if (!result.isConfirmed) return;
    try {
        await deletePortfolio();
        store.showToast(t('deletePortfolioSuccess'), t('redirectingToCreatePortfolio'));
        router.push({ name: 'portfolioCreate' })
    } catch (error) {
        console.error(error);
        swalError(t('errorSwalTitle'), t('errorUnexpectedMessage'));
    }
}
</script>

<template>
    <section class="d-lg-flex flex-wrap align-items-center">
        <h2 class="col-12 title-1">{{ $t('ownedPortfolio') }}</h2>
        <article class="d-flex flex-wrap justify-align-center mb-3">
            <div class="bg-light p-2 rounded bg-opacity-75 col-md-8 d-flex">
                <i class=" bi bi-info-square px-2 fs-3"></i>
                <p class="ps-2 mb-0" style="margin-bottom: 1rem;">
                    {{ $t('portfolioHead') }} <br>
                    {{ $t('portfolioDesciption') }}
                </p>
            </div>
            <aside class="col-md-4 text-md-end my-3">
                <router-link :to="{ name: 'updatePortfolio' }" class="btn btn-outline-primary me-2">{{ $t('edit') }}
                </router-link>
                <button class="btn btn-dark" @click="handleDelete(portfolio)">{{ $t('remove') }}</button>
            </aside>
        </article>

        <article class="card col-lg-8 mb-3">
            <div class="row row-cols-lg-2 g-0">
                <img class="jm-img-cover" v-if="imagePath" :src="imagePath" :alt="portfolio.bandName" />
                <div class="card-body">
                    <h5 class="card-title title-1">{{ portfolio.bandName }}</h5>
                    <p v-if="portfolio.tagline" class="txt-body text-primary">{{ portfolio.tagline }}</p>
                    <p class="card-txt txt-body">
                        {{ portfolio.biography }}
                    </p>
                </div>
            </div>
            <div v-if="errorMsg" class="alert alert-danger w-100 text-center" role="alert">
                {{ errorMsg }}
            </div>
        </article>
    </section>

    <!-- <section class="mt-0">
        <h2 class="title-1">{{ $t('music') }}</h2>
        <article class="align-items-start d-lg-flex justify-content-between mb-4">
            <MusicCard />
            <MusicCard />
            <MusicCard />
        </article>
    </section> -->

    <section class="mt-0">
        <h2 class="title-1">{{ $t('offers') }}</h2>
        <div class="align-items-start d-lg-flex flex-wrap justify-content-between gap-2">
            <OfferCard v-for="offer in offers" :key="offer.id" v-bind="offer" />
        </div>
    </section>
</template>

<style scoped>
.jm-img-cover {
    object-fit: cover;
}
</style>
