<script setup>
import OfferForm from '@/components/OfferForm.vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';

const { t } = useI18n();
const router = useRouter();

const handleSuccess = () => {
    alert(t('successMessage'));
    router.push('/my-offers');
};
</script>

<template>
    <section class="d-lg-flex flex-wrap justify-content-center">
        <h1 class="title-1 col-12">{{ $t('publishNewOffer') }}</h1>
        <div class="p-4 col-lg-6 jm-card-border bg-light">
            <small><span class="text-danger">*</span> {{ $t('requiredFields') }}</small>
            <OfferForm mode="create" @success="handleSuccess" />
        </div>
    </section>
</template>

<style scoped>
.label-required::after {
    content: ' *';
    color: #f75d2e;
}
</style>
