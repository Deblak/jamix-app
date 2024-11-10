<script setup>
import CardItem from '@/components/CardItem.vue';
</script>

<template>
    <section>
        <h2 class="title-1">{{ results }}</h2>
        <article class="align-items-start d-lg-flex justify-content-between">
            <CardItem />
            <CardItem />
            <CardItem />
        </article>
        <article class="align-items-start d-lg-flex justify-content-between">
            <CardItem />
            <CardItem />
            <CardItem />
        </article>
        <article class="align-items-start d-lg-flex justify-content-between">
            <CardItem />
            <CardItem />
            <CardItem />
        </article>
    </section>
</template>