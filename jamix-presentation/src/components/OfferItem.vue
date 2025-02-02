<script setup>
//import SocialNetworkBar from './SocialNetworkBar.vue';

defineProps({
    id: Number,
    title: String,
    city: String,
    zipCode: Number,
    description: String,
    createdAt: String,
    instrument: String,
    style: String,
    goal: String
});

</script>

<template>
    <div class="jm-card-border mb-2">
        <h5 class="card-title title-2 mb-2">{{ title }}</h5>
        <p class="m-0 txt-body color-soft"> {{ city }} - {{ zipCode }} </p>

        <div class="card-header row">
            <div class="col-5">
                <img class="col-12" src="../assets/pictures/geo-chierchia-o-9-fSSiCT0-unsplash.jpg"
                    alt="photo de l'annonce">

            </div>
            <div class="col-7">
                <ul class="p-0 card-txt txt-body">
                    <li class="m-1 badge rounded-pill text-bg-primary">{{ instrument }}</li>
                    <li class="m-1 badge rounded-pill text-bg-warning">{{ style }}</li>
                    <li class="m-1 badge rounded-pill text-bg-danger text-wrap">{{ goal }}</li>
                </ul>
            </div>
        </div>

        <div class="card-body jm-shadow-box mt-3">
            <p class="px-3 py-2">{{ description }}
            </p>
        </div>

        <div class="card-footer txt-body color-primary d-flex justify-content-between align-items-center">
            {{ createdAt }}
            <a href="#" class="btn px-4 shadow btn-warning">{{ $t('contact') }}</a>
        </div>
        <!-- <div class="mt-3 text-end">
            <SocialNetworkBar v-if="$route.name === 'myOffer'" />
        </div> -->
    </div>
</template>