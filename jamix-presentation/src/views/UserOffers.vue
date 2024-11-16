<script setup>
import OfferItem from '@/components/OfferItem.vue';

</script>
<template>
    <section>
        <h2 class="title-1">{{ $t('myOffers') }}</h2>
        <button class="btn btn-warning"><i class="bi bi-plus-circle"></i>&nbsp;{{ $t('postNewOffer') }}</button>
        <div class="mt-2 row row-cols-lg-3 g-3 g-lg-5">
            <article>
                <OfferItem class="edit-mode" />
                <div class="text-end">
                    <button class="btn btn-outline-primary me-2">{{ $t('edit') }}</button>
                    <button class="btn btn-dark">{{ $t('remove') }}</button>
                </div>
            </article>

            <article>
                <OfferItem class="edit-mode" />
                <div class="text-end">
                    <button class="btn btn-outline-primary me-2">{{ $t('edit') }}</button>
                    <button class="btn btn-dark">{{ $t('remove') }}</button>
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