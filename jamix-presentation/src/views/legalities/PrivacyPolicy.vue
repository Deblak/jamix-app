<template>
    <main class="container my-4">
        <h1 class="h1">{{ $t('privacyPolicy.title') }}</h1>
        <h2 class="h4">{{ $t('privacyPolicy.sections.1.title') }}</h2>
        <p>{{ $t('privacyPolicy.sections.1.content.0') }}</p>

        <h2 class="h4">{{ $t('privacyPolicy.sections.2.title') }}</h2>
        <p>{{ $t('privacyPolicy.sections.2.content.0') }}</p>
        <ul class="legalities-list">
            <li>{{ $t('privacyPolicy.sections.2.list.0') }}</li>
            <li>{{ $t('privacyPolicy.sections.2.list.1') }}</li>
            <li>{{ $t('privacyPolicy.sections.2.list.2') }}</li>
        </ul>
        <p>{{ $t('privacyPolicy.sections.2.footer') }}</p>

        <h2 class="h4">{{ $t('privacyPolicy.sections.3.title') }}</h2>
        <p>{{ $t('privacyPolicy.sections.3.content.0') }}</p>
        <ul class="legalities-list">
            <li>{{ $t('privacyPolicy.sections.3.list.0') }}</li>
            <li>{{ $t('privacyPolicy.sections.3.list.1') }}</li>
            <li>{{ $t('privacyPolicy.sections.3.list.2') }}</li>
        </ul>

        <h2 class="h4">{{ $t('privacyPolicy.sections.4.title') }}</h2>
        <p>{{ $t('privacyPolicy.sections.4.content.0') }}<br>
            {{ $t('privacyPolicy.sections.4.content.1') }}</p>

        <h2 class="h4">{{ $t('privacyPolicy.sections.5.title') }}</h2>
        <p>{{ $t('privacyPolicy.sections.5.content.0') }}</p>

        <h2 class="h4">{{ $t('privacyPolicy.sections.6.title') }}</h2>
        <p>{{ $t('privacyPolicy.sections.6.content.0') }}</p>
        <p>{{ $t('privacyPolicy.sections.7.content.0') }} <a
                href="https://github.com/Deblak/jamix-app/issues">https://github.com/Deblak/jamix-app/issues</a></p>
    </main>
</template>
<style>
.legalities-list {
    list-style-type: initial;
    margin: 1rem 3rem;
}
</style>