<script setup>
import OfferItem from '@/components/OfferItem.vue';
import { onMounted } from 'vue';
import { fetchUserOffer, offerItems, deleteOffer } from '@/services/offerService';
import { useI18n } from 'vue-i18n';
import { useAppStore } from '@/stores/useAppStore';
import { useSwalFire } from '@/composables/useSwalFire';
const store = useAppStore();
const { swalConfirm, swalError } = useSwalFire();

onMounted(() => {
    fetchUserOffer();
})
const { t } = useI18n();

const offers = offerItems;
async function handleDelete(id) {
    const result = await swalConfirm(t('confirmOfferDelete'));

    if (!result.isConfirmed) return;

    try {
        await deleteOffer(id);
        store.showToast(t('deleteOfferSuccess'), t('redirectingToOwnedOffers'));
    } catch (error) {
        console.error(error);
        swalError(t('errorSwalTitle'), t('errorUnexpectedMessage'));
    }
}

</script>
<template>
    <section>
        <h2 class="title-1">{{ $t('ownedOffers') }}</h2>
        <router-link :to="{ name: 'offerCreate' }" class="btn btn-warning"><i class="bi bi-plus-circle"></i>&nbsp;{{
            $t('postNewOffer')
        }}</router-link>

        <div class="mt-2 row row-cols-lg-3 g-3 g-lg-5">
            <article v-for="offer in offers" :key="offer.id">
                <OfferItem class="edit-mode" :key="offer.id" :id="offer.id" :title="offer.title"
                    :description="offer.description" :createdAt="offer.createdAt" :instrument="offer.instrumentName"
                    :style="offer.styleName" :goal="offer.goalType" :imageUrl="offer.imageUrl" :city="offer.city"
                    :zipCode="offer.zipCode" />
                <div class="text-end">
                    <router-link :to="{ name: 'updateOffer', params: { id: offer.id } }"
                        class="btn btn-outline-primary me-2">{{ $t('edit') }}
                    </router-link>
                    <button class="btn btn-jm-dark" @click="handleDelete(offer.id)">{{ $t('remove') }}</button>
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