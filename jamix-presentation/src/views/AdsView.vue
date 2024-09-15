<script setup>
import { ref, onMounted } from 'vue';
import CardItem from '@/components/CardItem.vue';
import axios from 'axios';
const adItems = ref([]);

onMounted(() => {
    axios.get('/ads')
        .then(response => {
            adItems.value = response.data;
            console.log(response);
        })
        .catch(error => {
            console.error('Error catch: ' + error);
        });
});
</script>

<template>
    <section>
        <h2 class="title-1">Résultats</h2>
        <article class="align-items-start d-lg-flex justify-content-between" v-for="ad in adItems" :key="ad.id">
            <CardItem :ad="ad" />
        </article>
    </section>
</template>