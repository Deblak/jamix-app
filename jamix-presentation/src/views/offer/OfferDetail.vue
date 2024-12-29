<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';

const route = useRoute();

const offerData = ref({
    title: '',
    description: '',
    city: '',
    zipCode: '',
    createdAt: null
});

defineProps(['id']);

async function fetchOffers() {
    try {
        const response = await axios.get(`http://localhost:8080/offers/${route.params.id}`);
        offerData.value = response.data;
    } catch (error) {
        console.error('An error has occurred:', error);
    }
}
onMounted(() => {
    fetchOffers();
})
</script>
<template>
    <main class="container">
        <h2 class="title-1">
            <RouterLink to="/results" class="navbar-brand"><i class="bi bi-arrow-left"></i>{{ $t('previousResults') }}
            </RouterLink>
        </h2>
        <section class="mt-2 row row-cols-lg-2 g-3 g-lg-5">
            <article class="order-1 order-lg-0 ">
                <div >
                    <RouterLink to="/portfolio" class="jm-highlight-card"><img
                            src="../../assets/pictures/elizeu-dias-29QO6oX3GlA-unsplash.jpg"
                            class="card-img-top jm-highlight-card" alt="Image">
                    </RouterLink>
                    <!-- <div class="mt-3 text-end">
                    <SocialNetworkBar v-if="$route.name === 'myOffer'" />
                </div> -->
                    <!-- <div class="mt-3 text-end">
                    <SocialNetworkBar />
                </div> -->
                </div>
            </article>
            <article>
                <div class="jm-card-border mb-2">
                    <h5 class="card-title title-2 mb-2">{{ offerData.title }}</h5>
                    <p class="m-0 txt-body color-soft"> {{ offerData.city }} - {{ offerData.zipCode }} </p>

                    <div class="card-header row align-items-center">
                        <div class="col-3">
                            <img class="col-12" src="../../assets/pictures/geo-chierchia-o-9-fSSiCT0-unsplash.jpg"
                                alt="image de profil">
                        </div>
                        <div class="col-7">
                            <!--card-text ?-->
                            <ul class="p-0 card-txt txt-body">
                                <li class="m-1 badge rounded-pill text-bg-primary">Chant</li>
                                <li class="m-1 badge rounded-pill text-bg-warning">Pop Rock</li>
                                <li class="m-1 badge rounded-pill text-bg-danger">Monter un groupe</li>
                            </ul>
                        </div>
                    </div>

                    <div class="card-body jm-shadow-box mt-3">
                        <p class="px-3 py-2">{{ offerData.description }}
                        </p>
                    </div>

                    <div class="card-footer txt-body color-primary d-flex justify-content-between align-items-center">
                        {{ $formatDate(offerData.createdAt) }}
                        <a href="#" class="btn px-4 shadow btn-warning">{{ $t('contact') }}</a>
                    </div>

                </div>
            </article>
        </section>
    </main>
</template>
<style scoped>
ul{
    display: flex;
    flex-direction: column;
}
ul > li {
    width: fit-content;
}
</style>