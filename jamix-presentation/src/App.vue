<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import FooterComponent from './components/FooterComponent.vue';

</script>

<template>
  <header class="bkg-highlight sticky-top">
    <nav class="navbar navbar-expand-lg">
      <RouterLink to="/" class="navbar-brand">
        <img src="../logo-jamix.png" alt="Jamix logo" height="48">
      </RouterLink>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <form class="d-flex col-12 col-lg-5" role="search">
        <input class="form-control  me-2" type="search" placeholder="Guitare, chanteur, ..." aria-label="Search">
        <RouterLink to="results" class="btn btn-outline-primary"><i class="bi bi-search"></i></RouterLink>
      </form>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav col-lg-12 d-flex justify-content-end">
          <li class="d-flex justify-content-end mb-2 mb-lg-0 mt-2 mt-lg-0 me-lg-3">
            <RouterLink to="/my-offer" class="btn px-4 btn-outline-primary">My offers</RouterLink>
          </li>
          <li class="d-flex justify-content-end mb-2 mb-lg-0 mt-2 mt-lg-0 me-lg-3">
            <RouterLink to="/connection" class="btn px-4 btn-outline-primary">Connexion</RouterLink>
          </li>
          <li class="d-flex justify-content-end mt-2 mt-lg-0">
            <RouterLink to="/inscription" class="btn px-4 btn-outline-primary">Inscription</RouterLink>
          </li>
        </ul>
      </div>
      <!-- <RouterLink to=" /">Home</RouterLink> -->
      <!-- <RouterLink to="/" class="navbar-brand">
        <img src="../logo-jamix.png" alt="Jamix logo" height="48">
      </RouterLink>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav col-lg-12 d-flex justify-content-end">
          <li class="d-flex justify-content-end mb-2 mb-lg-0 mt-2 mt-lg-0 me-lg-3">
            <RouterLink to="/connection" class="btn px-4 btn-outline-primary">Connection</RouterLink>
          </li>
          <li class="d-flex justify-content-end mt-2 mt-lg-0">
            <RouterLink to="/connection" class="btn px-4 btn-primary">Inscription</RouterLink>
          </li>
        </ul>
      </div> -->
    </nav>
  </header>
  <RouterView />
  <FooterComponent />

</template>
