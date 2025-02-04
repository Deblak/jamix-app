<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';
import { useI18n } from 'vue-i18n';

const route = useRoute();

const offer = ref({
    title: '',
    description: '',
    city: '',
    zipCode: '',
    createdAt: null,
    instrument: '',
    style: '',
    goal: '',
    imageId: ''
});

defineProps(['id']);
const { t } = useI18n();

async function fetchOffers() {
    try {
        const response = await axios.get(`http://localhost:8080/offers/${route.params.id}`);
        offer.value = response.data;
    } catch (error) {
        console.error(t('errorUnexpected'), error);
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
                <div>
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
                    <h5 class="card-title title-2 mb-2">{{ offer.title }}</h5>
                    <p class="m-0 txt-body color-soft"> {{ offer.city }} - {{ offer.zipCode }} </p>

                    <div class="card-header row align-items-center">
                        <div class="col-3">
                            <img class="col-12" :src="offer.imageId" alt="photo de l'annonce">
                        </div>
                        <div class="col-7">
                            <!--card-text ?-->
                            <ul class="p-0 card-txt txt-body">
                                <li class="m-1 badge rounded-pill text-bg-primary">{{ offer.instrument.name }}</li>
                                <li class="m-1 badge rounded-pill text-bg-warning">{{ offer.style.name }}</li>
                                <li class="m-1 badge rounded-pill text-bg-danger">{{ offer.goal.type }}</li>
                            </ul>
                        </div>
                    </div>

                    <div class="card-body jm-shadow-box mt-3">
                        <p class="px-3 py-2">{{ offer.description }}
                        </p>
                    </div>

                    <div class="card-footer txt-body color-primary d-flex justify-content-between align-items-center">
                        {{ $formatDate(offer.createdAt) }}
                        <a href="#" class="btn px-4 shadow btn-warning">{{ $t('contact') }}</a>
                    </div>

                </div>
            </article>
        </section>
    </main>
</template>
<style scoped>
ul {
    display: flex;
    flex-direction: column;
}

ul>li {
    width: fit-content;
}
</style>