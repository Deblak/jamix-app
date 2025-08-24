<template>
    <main class="bg-light text-center py-5">
        <h1 class="display-4 fw-semibold text-danger" role="alert">403</h1>
        <h2 class="mb-3 fw-bold text-secondary">{{ $t('forbiddenAccess') }}</h2>
        <p class="mb-4 text-muted">{{ $t('forbiddenMessage') }}</p>

        <router-link :to="{ name: 'home' }" class="btn btn-primary">
            {{ $t('homePage') }}
        </router-link>
    </main>
</template>
