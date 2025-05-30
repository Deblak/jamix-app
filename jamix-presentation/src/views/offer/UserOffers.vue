<script setup>
import OfferItem from '@/components/OfferItem.vue';
import { onMounted } from 'vue';
import { fetchUserOffer, offerItems, deleteOffer } from '@/services/offerService';
import { useI18n } from 'vue-i18n';
import { getImageUrl } from '@/utils/imagePath';

onMounted(() => {
    fetchUserOffer();
})
const { t } = useI18n();

const offers = offerItems;
function handleDelete(id) {
    if (confirm(t('confirmDelete'))) {
        deleteOffer(id);
    }
}

</script>
<template>
    <section>
        <h2 class="title-1">{{ $t('myOffers') }}</h2>
        <RouterLink to="/offer-create" class="btn btn-warning"><i class="bi bi-plus-circle"></i>&nbsp;{{
            $t('postNewOffer')
            }}</RouterLink>

        <div class="mt-2 row row-cols-lg-3 g-3 g-lg-5">
            <article v-for="offer in offers" :key="offer.id">

                <OfferItem class="edit-mode" :key="offer.id" :id="offer.id" :title="offer.title"
                    :description="offer.description" :createdAt="offer.createdAt" :instrument="offer.instrumentName"
                    :style="offer.styleName" :goal="offer.goalType" :imageUrl="offer.imageUrl" />
                <div class="text-end">
                    <button class="btn btn-outline-primary me-2">{{ $t('edit') }}</button>
                    <button class="btn btn-dark" @click="handleDelete(offer.id)">{{ $t('remove') }}</button>
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