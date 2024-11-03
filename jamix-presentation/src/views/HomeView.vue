<script setup>
import WelcomeComponant from '@/components/WelcomeComponant.vue';
import CardItem from '@/components/CardItem.vue';

import { onMounted } from 'vue';
import { fetchData, adItems } from '@/services/adService';
import { computed } from 'vue';

const maxdAds = computed(() => ads.value.slice(0, 3));

onMounted(() => {
  fetchData();
})
const ads = adItems;

</script>

<template>
  <main>
    <!-- SEARCH OFFERS-->
    <WelcomeComponant />
    <!-- RECENT OFFERS-->
    <section>
      <h2 class="title-1">En vedette</h2>
      <article class="align-items-start d-lg-flex justify-content-between" v-if="ads.length > 3">
        <CardItem v-for="ad in maxdAds" :key="ad.id" :id="ad.id" :title="ad.title" :description="ad.description"
          :createdAt="ad.created_at" />
      </article>
    </section>
  </main>
</template>
