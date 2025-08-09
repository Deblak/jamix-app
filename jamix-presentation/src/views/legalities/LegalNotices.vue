<template>
    <main class="container my-4">
        <h1 class="h1">{{ $t('legalNotices') }}</h1>

        <p>{{ $t('legal.description') }}</p>

        <h2 class="h4">{{ $t('legal.editorTitle') }}</h2>
        <p>{{ $t('legal.editorDescription') }}<br>
            {{ $t('legal.contact') }} <a
                href="https://github.com/Deblak/jamix-app/issues">https://github.com/Deblak/jamix-app/issues</a></p>

        <h2 class="h4">{{ $t('legal.hostingTitle') }}</h2>
        <p>{{ $t('legal.hostingDescription') }}</p>
        <ul>
            <li>{{ $t('legal.hostingName') }}</li>
            <li>{{ $t('legal.hostingAddress') }}</li>
            <li>{{ $t('legal.hostingWebsite') }}<a href="https://www.ovhcloud.com">https://www.ovhcloud.com</a></li>
        </ul>
    </main>
</template>
