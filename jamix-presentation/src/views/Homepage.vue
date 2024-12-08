<script setup>
import HomepageSearch from '@/components/HomepageSearch.vue';
import CardItem from '@/components/CardItem.vue';
import { onMounted } from 'vue';
import { fetchData, offerItems } from '@/services/offerService';
import { computed } from 'vue';

const maxOffers = computed(() => offers.value.slice(0, 3));

onMounted(() => {
  fetchData();
})
const offers = offerItems;

</script>

<template>
  <main>
    <!-- SEARCH OFFERS-->
    <HomepageSearch />

    <section class="container">
      <div class="row justify-content-center gx-5">
        <div class="col-6">
          <img src="../../public/crowd.jpg" class="rounded img-fluid" alt="Crowd">
        </div>
        <div class="col-6">
          <img src="../../public/sax.jpg" class="rounded img-fluid" alt="Saxophone">
        </div>
      </div>
    </section>

    <!-- RECENT OFFERS-->
    <section>
      <h2 class="title-1">{{ $t('featured') }}</h2>
      <article class="align-items-start d-lg-flex justify-content-between" v-if="offers.length > 3">
        <CardItem v-for="offer in maxOffers" :key="offer.id" :id="offer.id" :title="offer.title"
          :description="offer.description" :createdAt="offer.created_at" />
      </article>
    </section>
  </main>
</template>
