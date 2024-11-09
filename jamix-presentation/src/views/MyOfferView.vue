<script setup>
import OfferItem from '@/components/OfferItem.vue';
</script>
<template>
    <section>
        <h2 class="title-1">Mes annonces</h2>
        <button class="btn button-secondary"><i class="bi bi-plus-circle"></i>&nbsp;Déposer une nouvelle
            annonce</button>
        <div class="mt-2 row row-cols-lg-3 g-3 g-lg-5">
            <article>
                <OfferItem class="edit-mode" />
                <div class="text-end">
                    <button class="btn button-outline-primary me-2">Éditer</button>
                    <button class="btn button-black">Supprimer</button>
                </div>
            </article>

            <article>
                <OfferItem class="edit-mode" />
                <div class="text-end">
                    <button class="btn button-outline-primary me-2">Éditer</button>
                    <button class="btn button-black">Supprimer</button>
                </div>
            </article>
        </div>
    </section>
</template>
<style setup>
.edit-mode {
    background: rgba(216, 216, 216, 0.2);
    border-style: dashed solid;
    border-width: 3px;
    filter: grayscale(20%);
    width: 100%;
}
</style>