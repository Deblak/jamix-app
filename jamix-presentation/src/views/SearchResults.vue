<script setup>
import CardItem from '@/components/CardItem.vue';
import { onMounted } from 'vue';
import { fetchOffers, offerItems } from '@/services/offerService';


onMounted(() => {
    fetchOffers();
})
const offers = offerItems;
</script>

<template>
    <section>
        <h2 class="title-1">{{ $t('results') }}</h2>
        <!-- <article class="align-items-start d-lg-flex-wrap justify-content-between" v-for="offer in offers"
            :key="offer.id"> -->
        <div class="d-flex col-12" v-for="offer in offers" :key="offer.id">
            <CardItem :key="offer.id" :id="offer.id" :title="offer.title" :description="offer.description"
                :city="offer.city" :zipCode="offer.zipCode" :createdAt="offer.created_at" />
        </div>
    </section>
</template>