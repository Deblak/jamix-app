<script setup>
import apiClient from './services/axiosApi.js';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import FooterItem from './components/FooterItem.vue';
import ToasterMessage from '@/components/ToasterMessage.vue'
import { useAuth } from '@/stores/useAuthStore';
const router = useRouter();
const auth = useAuth();
const searchQuery = ref('');

const searchOffers = async () => {
  if (searchQuery.value.length > 3) {
    try {
      await apiClient.get('/offers', {
        params: { title: searchQuery.value }
      });
      router.push({ name: 'offerResults', query: { keyword: searchQuery.value } });
    } catch (error) {
      console.error('Error fetching search results', error);
    }
  }
};
</script>
<template>
  <header class="bg-dark sticky-top">
    <nav class="navbar navbar-expand-lg mb-0">
      <router-link :to="{ name: 'home' }" class="navbar-brand">
        <img src="./assets/icons/logo-jamix.png" alt="Jamix home" height="48">
      </router-link>

      <button class="navbar-toggler nav-btn" type="button" data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
        aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="col-12 col-lg-5">
        <form class="input-group" @submit.prevent="searchOffers">
          <input class="form-control radius-square" type="search" id="searchbar" :placeholder="$t('searchOfferPlaceholder')"
            aria-label="Search" v-model="searchQuery">
          <button type="submit" class="btn btn-jm-primary"><i class="bi bi-search"></i></button>
        </form>
      </div>

      <div class="collapse navbar-collapse col-7" id="navbarSupportedContent">
        <router-link :to="{ name: 'offerCreate' }"
          class="d-flex btn px-4 btn-jm-primary ms-lg-4 my-2 my-lg-0 me-lg-3"><i class="bi bi-plus-circle me-2"></i>{{
            $t('newOffer') }}
        </router-link>
        <template v-if="auth.isAuthenticated">
          <ul class="navbar-nav col-xl-8 justify-content-end align-items-center">
            <li class="ms-lg-2 my-2 my-lg-0 me-lg-3">
              <router-link :to="{ name: 'ownedOffers' }" class="btn btn-light btn-sm px-4">{{
                $t('ownedOffers') }}
              </router-link>
            </li>
            <li class="ms-lg-2 my-2 my-lg-0 me-lg-3">
              <router-link :to="{ name: 'ownedPortfolio' }" class="btn px-4 btn-light btn-sm">{{
                $t('portfolio') }}
              </router-link>
            </li>
            <li class="my-2 my-lg-0 me-lg-3">
              <button @click="auth.logout" class="btn px-4 btn-light btn-sm">Déconnexion</button>
            </li>
          </ul>
        </template>

        <template v-else>
          <ul class="navbar-nav col-xl-8 justify-content-end">
            <li class="my-2 my-lg-0 me-lg-3">
              <router-link :to="{ name: 'login' }" class="btn btn-light btn-sm px-4">{{ $t('login') }}
              </router-link>
            </li>
            <li class="mt-2 mt-lg-0">
              <router-link :to="{ name: 'signUp' }" class="btn btn-light btn-sm px-4 ">{{ $t('signUp')
              }}
              </router-link>
            </li>
          </ul>
        </template>

      </div>
    </nav>
  </header>
  <ToasterMessage />
  <RouterView />
  <FooterItem />

</template>
<style setup>
.nav-btn {
  background-color: #f3f1f5;
  border-radius: 18px;
}
.nav-btn:focus {
  background-color: rgb(191, 185, 229.4);
}
</style>
