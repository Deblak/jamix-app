<script setup>
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { userPortfolio, fetchUserPortfolio } from '@/services/portfolioService';
import PortfolioForm from '@/components/PortfolioForm.vue';
import { useAppStore } from '@/stores/useAppStore';
import { useSwalFire } from '@/composables/useSwalFire';

const { t } = useI18n();
const router = useRouter();
const store = useAppStore();
const { swalError } = useSwalFire();

const handleSuccess = (message) => {
    store.showToast(message, t('redirectingToOwnedPortfolio'));
    router.push({ name: 'ownedPortfolio' });
};
const handleError = (message) => {
    swalError(t('errorSwalTitle'), message || t('errorUnexpectedMessage'));
};
onMounted(async () => {
    try {
        await fetchUserPortfolio();
    } catch (err) {
        handleError(err.message || t('errorLoadingMessage'));
    }
});
</script>

<template>
    <section class="d-lg-flex flex-wrap justify-content-center">
        <h1 class="title-1 col-12">{{ $t('editPortfolio') }}</h1>
        <div class="p-4 col-lg-6 jm-card-border bg-light">
            <div v-if="!userPortfolio">{{ $t('loading') }}...</div>
            <div v-else>
                <p class="small">{{ $t('requireLegend') }}</p>
                <PortfolioForm :initialData="userPortfolio" mode="edit" @success="handleSuccess" @error="handleError" />
            </div>
        </div>
    </section>
</template>