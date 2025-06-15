<script setup>
import { onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { fetchOfferById, selectedOffer } from '@/services/offerService';
import OfferForm from '@/components/OfferForm.vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const route = useRoute();
const router = useRouter();
const offerId = route.params.id;

onMounted(() => {
    fetchOfferById(offerId);
});

const handleSuccess = () => {
    router.push('/my-offers');
};
</script>

<template>
    <section class="d-lg-flex flex-wrap justify-content-center">
        <h1 class="title-1 col-12">{{ $t('editOffer') }}</h1>
        <div class="p-4 col-lg-6 jm-card-border bg-light">
            <div v-if="!selectedOffer">{{ $t('loading') }}...</div>
            <div v-else>
                <OfferForm :initialData="selectedOffer" mode="edit" @success="handleSuccess" @error="handleError" />
            </div>
        </div>
    </section>
</template>