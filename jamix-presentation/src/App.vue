<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import FooterComponent from './components/FooterComponent.vue';

</script>
<template>
  <header class="bkg-highlight sticky-top">
    <nav class="navbar navbar-expand-lg">
      <RouterLink to="/" class="navbar-brand">
        <img src="./assets/icons/logo-jamix.png" alt="Jamix logo" height="48">
      </RouterLink>

      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="col-12 col-lg-5">
        <form class="input-group">
          <input class="form-control" type="search" id="searchbar" :placeholder="$t('searchPlaceholder')"
            aria-label="Search">
          <RouterLink to="results" class="btn btn-primary"><i class="bi bi-search"></i></RouterLink>
        </form>
      </div>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <RouterLink to="/new-offer" class="d-flex btn px-4 btn-primary ms-lg-4 my-2 my-lg-0 me-lg-3"><i
            class="bi bi-plus-circle me-2"></i>{{ $t('newOffer') }}
        </RouterLink>
        <ul class="navbar-nav col-lg-12 d-flex">
          <li class="ms-lg-2 my-2 my-lg-0 me-lg-3">
            <RouterLink to="/my-offer" class="btn px-4 btn-outline-primary btn-sm rounded-pill">{{ $t('myOffers') }}
            </RouterLink>
          </li>
          <li class="my-2 my-lg-0 me-lg-3">
            <RouterLink to="/connection" class="btn px-4 btn-outline-primary btn-sm rounded-pill">{{ $t('logIn') }}
            </RouterLink>
          </li>
          <li class="mt-2 mt-lg-0">
            <RouterLink to="/inscription" class="btn px-4 btn-outline-primary btn-sm rounded-pill">{{ $t('signUp') }}
            </RouterLink>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <RouterView />
  <FooterComponent />

</template>
