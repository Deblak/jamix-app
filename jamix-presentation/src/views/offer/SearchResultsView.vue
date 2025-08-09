<script setup>
import { ref, onMounted, watch } from 'vue';
import apiClient from '@/services/axiosApi';
import { useRoute } from 'vue-router';
import OfferCard from '@/components/OfferCard.vue';
import DOMPurify from 'dompurify';

function purifyInput(input) {
    return DOMPurify.sanitize(input);
};

const offers = ref([]);
const route = useRoute();
const title = ref(route.query.keyword);

const searchOffers = async () => {
    const search = purifyInput(title.value.trim());
    if (search) {
        try {
            const response = await apiClient.get('/offers', {
                params: { title: search },
            });
            offers.value = response.data;
        } catch (error) {
            console.error('Error fetching search results', error);
        }
    }
};

onMounted(searchOffers);
watch(() => route.query.keyword, (newTitle) => {
    title.value = newTitle;
    searchOffers();
});
</script>

<template>
    <main class="container-custom">
        <h2 class="title-1">{{ $t('results') }}</h2>
        <div class="row g-5">
            <div class="col-12 col-md-6 col-lg-4 col-xl-2" v-for="offer in offers" :key="offer.id">
                <OfferCard :key="offer.id" :id="offer.id" :title="offer.title" :description="offer.description"
                    :city="offer.city" :zipCode="offer.zipCode" :createdAt="offer.createdAt"
                    :instrument="offer.instrumentName" :style="offer.styleName" :goal="offer.goalType"
                    :imageUrl="offer.imageUrl" />
            </div>
        </div>
    </main>
</template>