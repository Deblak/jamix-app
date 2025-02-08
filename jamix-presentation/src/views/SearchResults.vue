<script setup>
import { ref, onMounted, watch } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
import OfferCard from '@/components/OfferCard.vue';
import DOMPurify from 'dompurify';

function purifyInput(input) {
    return DOMPurify.sanitize(input);
};

const offers = ref([]);
const route = useRoute();
const keyword = ref(route.query.keyword);

const searchOffers = async () => {
    const search = purifyInput(keyword.value.trim());
    if (search) {
        try {
            const response = await axios.get('http://localhost:8080/offers/search', {
                params: { keyword: keyword.value },
            });
            offers.value = response.data;
        } catch (error) {
            console.error('Error fetching search results', error);
        }
    }
};

onMounted(searchOffers);
watch(() => route.query.keyword, (newKeyword) => {
    keyword.value = newKeyword;
    searchOffers();
});
</script>

<template>
    <main class="container">
        <h2 class="title-1">{{ $t('results') }}</h2>
        <div class="row g-5">
            <div class="col-12 col-md-6 col-lg-4" v-for="offer in offers" :key="offer.id">
                <OfferCard :key="offer.id" :id="offer.id" :title="offer.title" :description="offer.description"
                    :city="offer.city" :zipCode="offer.zipCode" :createdAt="offer.createdAt"
                    :instrument="offer.instrument.name" :style="offer.style.name" :goal="offer.goal.type" />
            </div>
        </div>
    </main>
</template>