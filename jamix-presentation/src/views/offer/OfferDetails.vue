<script setup>
import { onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { fetchOfferById, selectedOffer } from '@/services/offerService';
import { getOfferImageUrl, getPortfolioImageUrl } from '@/utils/imagePath';

const route = useRoute();
const { t } = useI18n();

const offer = computed(() => selectedOffer.value);
const portfolio = computed(() => offer.value?.portfolioLinkDto ?? null);

onMounted(() => {
    fetchOfferById(route.params.id)
})
</script>

<template>
    <main class="container-custom" role="main">
        <!-- Screen reader -->
        <h1 class="visually-hidden">{{ t('details') }}</h1>

        <h2 class="title-1">
            <router-link :to="{ name: 'offerResults' }" class="navbar-brand">
                <i class="bi bi-arrow-left" aria-hidden="true"></i> {{ t('previousResults') }}
            </router-link>
        </h2>

        <section class="mt-2 row row-cols-lg-2 g-3 g-lg-5" v-if="offer">

            <article class="order-1 order-lg-0" v-if="portfolio">
                <div>
                    <router-link :to="{ name: 'portfolio', params: { id: portfolio.id } }" class="jm-highlight-card">
                        <img class="card-img-top radius-square" :src="getPortfolioImageUrl(portfolio.imageId)"
                            :alt="portfolio.bandName" />

                        <span class="hover-label" aria-hidden="true">
                            {{ $t('portfolioCta') }}
                        </span>
                    </router-link>
                    <p class="mt-2 mb-0 text-center txt-body fw-medium">
                        {{ portfolio.bandName }}
                    </p>
                </div>
            </article>

            <article>
                <div class="jm-card-border mb-2">
                    <h5 class="card-title title-2 mb-2">{{ offer.title }}</h5>
                    <p class="m-0 txt-body text-secondary fw-normal">{{ offer.city }} - {{ offer.zipCode }}</p>

                    <div class="card-header row align-items-center">
                        <div class="col-5 col-md-3">
                            <img class="col-12" :src="getOfferImageUrl(offer.imageUrl)" :alt="`${offer.title}`" />
                        </div>
                        <div class="col-7">
                            <ul class="p-0 card-txt txt-body">
                                <li class="m-1 badge text-bg-danger fw-medium txt-small">{{ offer.instrumentName }}</li>
                                <li class="m-1 badge text-bg-warning fw-medium txt-small">{{ offer.styleName }}</li>
                                <li class="m-1 badge text-bg-primary fw-medium txt-small">{{ offer.goalType }}</li>
                            </ul>
                        </div>
                    </div>

                    <div class="card-body jm-shadow-box mt-3">
                        <p class="px-3 py-2 description" v-text="offer.description"></p>
                    </div>

                    <div class="card-footer txt-body text-primary d-flex justify-content-between align-items-center">
                        <span>{{ $formatDate(offer.createdAt) }}</span>
                        <router-link :to="{ name: 'contactForm', params: { offerId: offer.id } }"
                            class="btn px-4 shadow btn-jm-warning">
                            {{ $t('contact') }}
                        </router-link>
                    </div>
                </div>
            </article>
        </section>
    </main>
</template>

<style scoped>
ul {
    display: flex;
    flex-direction: column;
}

ul>li {
    width: fit-content;
}

.visually-hidden {
    position: absolute !important;
    width: 1px;
    height: 1px;
    margin: -1px;
    padding: 0;
    overflow: hidden;
    clip: rect(0 0 0 0);
    white-space: nowrap;
    border: 0;
}
</style>
