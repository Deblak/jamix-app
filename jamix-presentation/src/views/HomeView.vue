<script setup>
import OfferCard from '@/components/OfferCard.vue';
import { onMounted, computed } from 'vue';
import { fetchOffers, offerItems } from '@/services/offerService';

const maxOffers = computed(() => offers.value.slice(0, 3));

onMounted(() => {
  fetchOffers();
})
const offers = offerItems;

</script>

<template>
  <main>
    <header class="mb-4 mb-lg-5 container-custom">
      <h1 class="header">{{ $t('searchFindPlay') }}</h1>
      <h2 class="txt-subtile">
        {{ $t('browseJoinFind') }}
      </h2>
    </header>
    <!-- SEARCH OFFERS-->
    <section class="container-custom my-md-5">
      <div class="row">
        <!-- Group -->
        <article class="col-s-12 col-md-6 mb-3 mb-md-0">
          <div class="card bg-image text-center">
            <img src="/crowd.jpg" class="card-img radius-square" alt="">
            <div class="card-img-overlay d-flex justify-content-center align-items-center">
              <router-link :to="{ name: 'offerResults' }" class="btn btn-jm-secondary btn-lg title-xl rounded-pill">
                {{ $t('findGroup') }}
              </router-link>
            </div>
          </div>
        </article>
        <!-- Portfolio -->
        <article class="col-md-6">
          <div class="card bg-image text-center">
            <img src="/sax.jpg" class="card-img radius-square" alt="">
            <div class="card-img-overlay d-flex justify-content-center align-items-center">
              <router-link :to="{ name: 'portfolioResults' }" class="btn btn-salmon btn-lg title-xl rounded-pill">
                {{ $t('browsePortfolios') }}
              </router-link>
            </div>
          </div>
        </article>
      </div>
    </section>

    <!-- RECENT OFFERS-->
    <section>
      <hr>
      <h2 class="title-1">{{ $t('featured') }}</h2>
      <article class="align-items-start d-lg-flex justify-content-between">
        <OfferCard v-for="offer in maxOffers" :key="offer.id" :id="offer.id" :title="offer.title"
          :description="offer.description" :city="offer.city" :zipCode="offer.zipCode" :createdAt="offer.createdAt"
          :instrument="offer.instrumentName" :style="offer.styleName" :goal="offer.goalType"
          :imageUrl="offer.imageUrl" />
      </article>
    </section>
  </main>
</template>
<style scope>
.radius-square {
  border-radius: 0.5rem;
}

.title-xl {
  font-family: 'Open Sans';
  font-size: clamp(1rem, 5vw, 1.5rem);
  font-weight: 650;
  padding: clamp(0.5rem, 5vw, 1rem) clamp(1rem, 5vw, 2rem)
}

h1.header {
  font-size: clamp(2.5rem, 5vw, 5rem);
  font-weight: bold;
  text-transform: uppercase;
}

header.h2 {
  font-size: 3rem;
}

.btn-salmon {
  background-color: #D64545;
  color: white;
}

.btn-salmon:hover,
.btn-salmon:focus {
  background-color: white;
  color: #C74C69;
  border-color: #C74D62;
}

.btn-salmon:active {
  background-color: #C74D62 !important;
  color: white !important;
}
</style>