<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import Footer from './components/Footer.vue';

const searchQuery = ref('');
const router = useRouter();

const searchOffers = async () => {
  if (searchQuery.value.length > 3) {
    try {
      const response = await axios.get(`http://localhost:8080/offers/search`, {
        params: { keyword: searchQuery.value },
      });
      router.push({ name: 'results', query: { keyword: searchQuery.value } });
    } catch (error) {
      console.error('Error fetching search results', error);
    }
  }
};
</script>
<template>
  <header class="bg-dark sticky-top">
    <nav class="navbar navbar-expand-lg">
      <RouterLink to="/" class="navbar-brand">
        <img src="./assets/icons/logo-jamix.png" alt="Jamix logo" height="48">
      </RouterLink>

      <button class="navbar-toggler btn-primary" type="button" data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
        aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="col-12 col-lg-5">
        <form class="input-group" @submit.prevent="searchOffers">
          <input class="form-control" type="search" id="searchbar" :placeholder="$t('searchPlaceholder')"
            aria-label="Search" v-model="searchQuery">
          <button type="submit" class="btn btn-primary"><i class="bi bi-search"></i></button>
        </form>
      </div>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <RouterLink to="/offer-create" class="d-flex btn px-4 btn-primary ms-lg-4 my-2 my-lg-0 me-lg-3"><i
            class="bi bi-plus-circle me-2"></i>{{ $t('newOffer') }}
        </RouterLink>
        <ul class="navbar-nav col-lg-auto d-flex">
          <li class="ms-lg-2 my-2 my-lg-0 me-lg-3">
            <RouterLink to="/my-offer" class="btn px-4 btn-light btn-sm rounded-pill">{{ $t('myOffers') }}
            </RouterLink>
          </li>
          <li class="my-2 my-lg-0 me-lg-3">
            <RouterLink to="/login" class="btn px-4 btn-light btn-sm rounded-pill">{{ $t('login') }}
            </RouterLink>
          </li>
          <li class="mt-2 mt-lg-0">
            <RouterLink to="/signUp" class="btn px-4 btn-light btn-sm rounded-pill">{{ $t('signUp') }}
            </RouterLink>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <RouterView />
  <Footer />

</template>
<style setup>
/* .btn-light {
  background-color: #b089d8;
}

.btn-light:hover {
  background-color: #b089d8;
  color: #f3f1f5;
} */
</style>
