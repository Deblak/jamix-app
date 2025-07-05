<script setup>
import { RouterLink } from 'vue-router';
</script>

<template>
    <main class="bg-light text-center py-5">
        <h1 class="display-4 fw-semibold text-warning" role="alert">403</h1>
        <h2 class="mb-3 fw-bold text-secondary">{{ $t('forbiddenAccess') }}</h2>
        <p class="mb-4 text-muted">{{ $t('forbiddenMessage') }}</p>

        <RouterLink to="/" class="btn btn-primary">
            {{ $t('homePage') }}
        </RouterLink>
    </main>
</template>
