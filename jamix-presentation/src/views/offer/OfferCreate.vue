<script setup>
import OfferForm from '@/components/OfferForm.vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';
import { useAppStore } from '@/stores/useAppStore';
import { useSwalFire } from '@/composables/useSwalFire';

const { t } = useI18n();
const router = useRouter();
const store = useAppStore();
const { swalError } = useSwalFire();

const handleSuccess = (message) => {
    store.showToast(message, t('redirectingToOwnedOffers'));
    router.push({ name: 'ownedOffers' })
};

const handleError = (message) => {
    if (message === 'invalid-location') {
        swalError(t('errorInvalidLocationTitle'), t('errorInvalidLocationMessage'));
    } else {
        swalError(t('errorSwalTitle'), message || t('errorUnexpectedMessage'));
    }
};
</script>

<template>
    <section class="d-lg-flex flex-wrap justify-content-center">
        <h1 class="title-1 col-12">{{ $t('publishNewOffer') }}</h1>
        <div class="p-4 col-lg-6 jm-card-border bg-light">
            <small>{{ $t('requireLegend') }}</small>
            <OfferForm mode="create" @success="handleSuccess" @error="handleError" />
        </div>
    </section>
</template>