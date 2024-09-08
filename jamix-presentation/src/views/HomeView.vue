<script setup lang="ts">
import WelcomeComponant from '@/components/WelcomeComponant.vue';
import CardItem from '@/components/CardItem.vue';
</script>

<template>
  <main>
    <!-- SEARCH OFFERS-->
    <WelcomeComponant />
    <!-- RECENT OFFERS-->
    <section>
      <h2 class="title-1">En vedette</h2>
      <article class="align-items-start d-lg-flex justify-content-between">
        <CardItem />
        <CardItem />
        <CardItem />
      </article>
    </section>
  </main>
</template>
