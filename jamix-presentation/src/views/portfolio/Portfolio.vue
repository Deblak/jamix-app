<script setup>
import { onMounted, computed, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { fetchPortfolioById, selectedPortfolio, portfolioLinkedOffers } from '@/services/portfolioService';
import { getPortfolioImageUrl } from '@/utils/imagePath';
import MusicCard from '@/components/MusicCard.vue';
import OfferCard from '@/components/OfferCard.vue';
const errorMsg = ref(null);
const { t } = useI18n();
const imagePath = computed(() => getPortfolioImageUrl(selectedPortfolio.value.imageUrl));
const offers = portfolioLinkedOffers;
const props = defineProps({
    id: {
        type: String,
        required: true
    }
});

onMounted(async () => {
    try {
        await fetchPortfolioById(props.id);
        if (!selectedPortfolio.value || !selectedPortfolio.value.id) {
            errorMsg.value = t('noPortfolioFound');
        }
    } catch (error) {
        errorMsg.value = error.message || t('error.loadingPortfolio');
    }
});

</script>
<template>
    <section class="d-lg-flex flex-wrap align-items-center">
        <article v-if="selectedPortfolio" class="card col-lg-8 mb-3">
            <div class="row row-cols-lg-2 g-0">
                <img class="jm-img-cover" v-if="imagePath" :src="imagePath" :alt="selectedPortfolio.bandName" />
                <div class="card-body">
                    <h5 class="card-title title-1">{{ selectedPortfolio.bandName }}</h5>
                    <p v-if="selectedPortfolio.tagline" class="txt-body text-primary">{{ selectedPortfolio.tagline }}
                    </p>
                    <p class="card-txt txt-body">
                        {{ selectedPortfolio.biography }}
                    </p>
                </div>
            </div>
        </article>
        <!-- <div class="col-lg-5 text-center">
            <span>
                <SocialNetworkBar />
            </span>
        </div> -->
    </section>

    <!-- <section>
        <h2 class="title-1">{{ $t('music') }}</h2>
        <article class="align-items-start d-lg-flex justify-content-between mb-4">
            <MusicCard />
            <MusicCard />
            <MusicCard />
        </article>
    </section> -->

    <section>
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