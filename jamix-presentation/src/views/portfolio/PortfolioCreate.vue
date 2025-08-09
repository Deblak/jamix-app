<script setup>
import PortfolioForm from '@/components/PortfolioForm.vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';
import { useAppStore } from '@/stores/useAppStore';
import { useSwalFire } from '@/composables/useSwalFire';

const { t } = useI18n();
const router = useRouter();
const store = useAppStore();
const { swalError } = useSwalFire();

const handleSuccess = (message) => {
    store.showToast(message, t('redirectingToOwnedPortfolio'));
    router.push({ name: 'ownedPortfolio' })
};
const handleError = (message) => {
    swalError(t('errorSwalTitle'), message || t('errorUnexpectedMessage'));
};

</script>

<template>
    <section class="d-lg-flex flex-wrap justify-content-center">
        <h1 class="title-1 col-12">{{ $t('publishPortfolio') }}</h1>
        <article class="d-flex flex-wrap mb-3 col-12 justify-content-center">
            <div class="bg-light p-2 rounded bg-opacity-75 col-lg-6 d-flex">
                <i class=" bi bi-info-square px-2 fs-3"></i>
                <p class="ps-2 mb-0" style="margin-bottom: 1rem;">
                    {{ $t('portfolioHead') }} <br>
                    {{ $t('portfolioDesciption') }}
                </p>
            </div>
        </article>
        <div class="p-4 col-lg-6 jm-card-border bg-light">
            <small>{{ $t('requireLegend') }}</small>
            <PortfolioForm mode="create" @success="handleSuccess" @error="handleError" />
        </div>
    </section>
</template>