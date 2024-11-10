<script setup>
import SocNetworkItem from '@/components/SocNetworkItem.vue';
import MusicCard from '@/components/MusicCard.vue';
import CardItem from '@/components/CardItem.vue';
</script>
<template>
    <section class="d-lg-flex align-items-center">
        <article class="card col-lg-8 mb-3">
            <div class="row row-cols-lg-2 g-0">
                <img src="../assets/pictures/elizeu-dias-29QO6oX3GlA-unsplash.jpg" class="jm-img-cover" alt="Image">

                <div class="card-body">
                    <h5 class="card-title title-1">R'Stream</h5>
                    <p class="txt-body color-primary">Préparez vous à danser !</p>
                    <p class="card-txt txt-body">
                        Nous jouons autant de reprises que de compositions...
                        <br>Revivez avec nous les plus grandes voix de la Soul ou du
                        Jazz : Amy Whinehouse, Joss Stone, Stevie Wonder ou Bill Withers !
                    </p>
                </div>
            </div>
        </article>
        <div class="col-lg-5 text-center">
            <span>
                <SocNetworkItem />
            </span>
        </div>
    </section>
    <section>
        <h2 class="title-1">{{ music }}</h2>
        <article class="align-items-start d-lg-flex justify-content-between">
            <MusicCard />
            <MusicCard />
            <MusicCard />
        </article>
    </section>

    <!-- OFFERS-->
    <section>
        <h2 class="title-1">{{ offers }}</h2>
        <div class="align-items-start d-lg-flex justify-content-between">
            <CardItem />
        </div>
    </section>
</template>