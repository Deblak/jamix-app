<template>
    <!--pt-1 bg-light sticky-bottom-->
    <footer class="mt-auto py-3 bg-light">
        <nav class="m-0 d-flex-column text-center">
            <router-link :to="{ name: 'home' }" class="navbar-brand">
                <img src="../assets/icons/logo-jamix.png" alt="Logo" height="48">
            </router-link> <br>
            &copy; {{ $t('copyright') }}
            <ul class="d-flex justify-content-center flex-wrap">
                <li><router-link :to="{ name: 'legalNotices' }" class="p-2 text-dark text-decoration-none">{{ $t('legalNotices') }}
                    </router-link>|</li>
                <li><router-link :to="{ name: 'termsOfUse' }" class="p-2 text-dark text-decoration-none">{{
                    $t('termsOfUse') }}
                    </router-link> |</li>

                <li><router-link :to="{ name: 'privacyPolicy' }" class="p-2 text-dark text-decoration-none">{{ $t('privacyPolicy.title') }}
                    </router-link></li>
            </ul>
        </nav>
    </footer>
</template>