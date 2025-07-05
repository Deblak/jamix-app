<script setup>
import { RouterLink } from 'vue-router';
</script>

<template>
    <main class="bg-light text-center py-5">
        <h1 class="display-4 fw-semibold text-danger" role="alert">500</h1>
        <h2 class="mb-3 fw-bold text-secondary">{{ $t('unexpectedError') }}</h2>
        <p class="mb-4 text-muted">{{ $t('unexpectedMessage') }}</p>

        <RouterLink to="/" class="btn btn-primary">
            {{ $t('goHome') }}
        </RouterLink>
    </main>
</template>
