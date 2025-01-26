<script setup>
//import HomepageSearch from '@/components/HomepageSearch.vue';
import OfferCard from '@/components/OfferCard.vue';
import { onMounted } from 'vue';
import { fetchOffers, offerItems } from '@/services/offerService';
import { computed } from 'vue';

const maxOffers = computed(() => offers.value.slice(0, 3));

onMounted(() => {
  fetchOffers();
})
const offers = offerItems;

</script>

<template>
  <main>
    <!-- <header> -->
    <header class="mb-4 mb-lg-5 container">
      <h1 class="header">{{ $t('searchFindPlay') }}</h1>
      <h2 class="txt-subtile">
        {{ $t('browseJoinFind') }}
      </h2>
    </header>
    <!-- SEARCH OFFERS-->
    <section class="container my-5">
      <div class="row">
        <!-- Group -->
        <article class="col-s-12 col-md-6 mb-3 mb-md-0">
          <div class="card bg-image text-center">
            <img src="../../crowd.jpg" class="card-img" alt="Image d'un concert avec des lumières" />
            <div class="card-img-overlay d-flex justify-content-center align-items-center">
              <a href="/recherche-groupe" class="btn btn-light btn-lg title-xl rounded-pill"
                aria-label="Trouver un groupe">{{ $t('findGroup') }}</a>
            </div>
          </div>
        </article>
        <!-- Portfolio -->
        <article class="col-md-6">
          <div class="card bg-image text-center">
            <img src="../../sax.jpg" class="card-img" alt="Image d'un saxophoniste" />
            <div class="card-img-overlay d-flex justify-content-center align-items-center">
              <a href="/portfolios" class="btn btn-light btn-lg title-xl rounded-pill"
                aria-label="Parcourir les portfolios">{{ $t('browsePortfolios') }}</a>
            </div>
          </div>
        </article>
      </div>
    </section>

    <!-- RECENT OFFERS-->
    <section>
      <hr>
      <h2 class="title-1">{{ $t('featured') }}</h2>
      <article class="align-items-start d-lg-flex justify-content-between" v-if="offers.length > 3">
        <OfferCard v-for="offer in maxOffers" :key="offer.id" :id="offer.id" :title="offer.title"
          :description="offer.description" :city="offer.city" :zipCode="offer.zipCode" :createdAt="offer.createdAt"
          :instrument="offer.instrument.name" :style="offer.style.name" :goal="offer.goal.type" />
      </article>
    </section>
  </main>
</template>
<style scope>
.title-xl {
  font-family: 'Open Sans';
  font-size: 1.5rem;
  font-weight: 650;
  background-color: white;
  padding: 1rem 2rem;
}

h1.header {
  font-size: 5rem;
  font-weight: bold;
  text-transform: uppercase;
}

header.h2 {
  font-size: 3rem;
}
</style>