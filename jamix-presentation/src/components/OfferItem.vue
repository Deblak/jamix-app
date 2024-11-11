<script setup>
import SocNetworkItem from './SocNetworkItem.vue';
import axiosApi from '@/services/axiosApi.js'
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const offerItems = ref(null);

defineProps(['id']);

async function fetchData() {
    try {
        const response = await axiosApi.get(`/offers/${route.params.id}`);
        offerItems.value = response.data;
    } catch (error) {
        console.error('An error has occured:', error)
    }
}
onMounted(() => {
    fetchData();
})
</script>

<template>
    <div v-if="offerItems" class="jm-card-border mb-2">
        <h5 class="card-title title-2 mb-2">{{ offerItems.title }}</h5>
        <p class="m-0 txt-body color-soft"> Poitiers - 86000 </p>

        <div class="card-header row">
            <div class="col-5">
                <img class="col-12" src="../../geo-chierchia-o-9-fSSiCT0-unsplash.jpg" alt="image de profil">

            </div>
            <div class="col-7">
                <!--card-text ?-->
                <ul class="p-0 card-txt txt-body">
                    <li class="m-1 theme-secondary">Chant</li>
                    <li class="m-1 theme-tertiary">Pop Rock</li>
                    <li class="m-1 theme-soft">Monter un groupe</li>
                </ul>
            </div>
        </div>

        <div class="card-body jm-shadow-box mt-3">
            <p class="px-3 py-2">{{ offerItems.description }}
            </p>
        </div>

        <div class="card-footer txt-body color-primary d-flex justify-content-between align-items-center">
            {{ offerItems.createdAt }}
            <a href="#" class="btn px-4 shadow btn-secondary">Contacter</a>
        </div>
        <div class="mt-3 text-end">
            <SocNetworkItem v-if="$route.name === 'myOffer'" />
        </div>
    </div>
    <div v-else>
        Veuillez réessayer...
    </div>
</template>