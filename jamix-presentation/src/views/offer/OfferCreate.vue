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
    store.showToast(message, t('redirectingToMyOffers'));
    router.push('/my-offers');
};

const handleError = (message) => {
    if (message === 'invalid-location') {
        swalError(t('errorInvalidLocationTitle'), t('errorInvalidLocationMessage'));
    } else {
        swalError(t('errorUnexpectedTitle'), message || t('errorUnexpectedMessage'));
    }
};
</script>

<template>
    <section class="d-lg-flex flex-wrap justify-content-center">
        <h1 class="title-1 col-12">{{ $t('publishNewOffer') }}</h1>
        <div class="p-4 col-lg-6 jm-card-border bg-light">
            <small><span class="text-danger">*</span> {{ $t('requiredFields') }}</small>
            <OfferForm mode="create" @success="handleSuccess" @error="handleError" />
        </div>
    </section>
</template>

<style scoped>
.label-required::after {
    content: ' * ';
    color: #f75d2e;
}
</style>
