<script setup>
import SocNetworkItem from '@/components/SocNetworkItem.vue';
import OfferItem from '@/components/OfferItem.vue';
</script>
<template>
    <section>
        <h2 class="title-1">
            <RouterLink to="/" class="navbar-brand"><i class="bi bi-arrow-left"></i>{{ $t('previousResults') }}
            </RouterLink>
        </h2>
        <article class="mt-2 row row-cols-lg-2 g-3 g-lg-5">
            <div class="order-1 order-lg-0 ">
                <RouterLink to="/portfolio" class="jm-highlight-card"><img
                        src="../assets/pictures/elizeu-dias-29QO6oX3GlA-unsplash.jpg"
                        class="card-img-top jm-highlight-card" alt="Image">
                </RouterLink>
                <div class="mt-3 text-end">
                    <SocNetworkItem />
                </div>
            </div>
            <OfferItem />
        </article>
    </section>
</template>