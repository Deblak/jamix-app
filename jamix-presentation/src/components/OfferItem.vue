<script setup>
//import SocialNetworkBar from './SocialNetworkBar.vue';
import { computed } from 'vue';
import { getOfferImageUrl } from '@/utils/imagePath';

const props = defineProps({
    id: Number,
    title: String,
    city: String,
    zipCode: String,
    description: String,
    createdAt: String,
    instrument: String,
    style: String,
    goal: String,
    imageUrl: String
});
const imagePath = computed(() => getOfferImageUrl(props.imageUrl));


</script>

<template>
    <div class="jm-card-border mb-2">
        <h5 class="card-title title-2 mb-2">{{ title }}</h5>
        <p class="m-0 txt-body text-secondary fw-normal"> {{ city }} - {{ zipCode }} </p>

        <div class="card-header row">
            <div class="col-5">
                <img class="col-12 img-user" v-if="imagePath" :src="imagePath" :alt="`Image ${title}`" />
            </div>
            <div class="col-7">
                <ul class="p-0 card-txt txt-body">
                    <li class="m-1 badge text-bg-primary">{{ instrument }}</li>
                    <li class="m-1 badge text-bg-warning">{{ style }}</li>
                    <li class="m-1 badge text-bg-danger text-white text-wrap">{{ goal }}</li>
                </ul>
            </div>
        </div>

        <div class="card-body jm-shadow-box mt-3">
            <p class="px-3 py-2">{{ description }}
            </p>
        </div>

        <div class="card-footer txt-body text-primary d-flex justify-content-between align-items-center">
            {{ createdAt }}
            <button class="btn px-4 btn-warning" disabled>{{ $t('contact') }}</button>
        </div>
        <!-- <div class="mt-3 text-end">
            <SocialNetworkBar v-if="$route.name === 'ownedOffers'" />
        </div> -->
    </div>
</template>