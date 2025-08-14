<template>
    <main class="bg-light text-center py-5">
        <h1 class="display-4 fw-semibold text-danger" role="alert">404</h1>
        <h2 class="mb-3 fw-bold text-secondary">{{ $t('pageNotFound') }}</h2>
        <p class="mb-4 text-muted">{{ $t('pageNotFoundMessage') }}</p>
        <img src="../../../public/error 404.jpg" class="col-4 col-md-2 img-fluid rounded d-block mx-auto mb-4"
            alt="`${toto}`" />

        <router-link :to="{ name: 'login' }" class="btn btn-primary">
            {{ $t('login') }}
        </router-link>
    </main>
</template>
