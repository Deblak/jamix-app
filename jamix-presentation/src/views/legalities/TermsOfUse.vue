<template>
  <main class="container my-4">
    <h1 class="h1">{{ $t('termsOfUse') }}</h1>

    <p>
      <strong>{{ $t('terms.application') }} :</strong> {{ $t('terms.appName') }}<br />
      <strong>{{ $t('terms.version') }} :</strong> {{ $t('terms.versionNumber') }}<br />
      <strong>{{ $t('terms.effectiveDate') }} :</strong> {{ $t('terms.effectiveDateValue') }}
    </p>

    <h2 class="h4">{{ $t('terms.sections.1.title') }}</h2>
    <p>
      {{ $t('terms.sections.1.prefix') }}
      <strong>{{ $t('terms.appName') }}</strong>,
      <a href="https://acacia.readresolve.tech" target="_blank">{{ $t('terms.sections.1.linkText') }}</a>.
      {{ $t('terms.sections.1.suffix') }}
    </p>
    <p>{{ $t('terms.sections.1.content') }}</p>

    <h2 class="h4">{{ $t('terms.sections.2.title') }}</h2>
    <p>{{ $t('terms.sections.2.content') }}</p>

    <h2 class="h4">{{ $t('terms.sections.3.title') }}</h2>
    <p>{{ $t('terms.sections.3.content1') }}</p>
    <p>{{ $t('terms.sections.3.content2') }}</p>
    <ul class="legalities-list">
      <li>{{ $t('terms.sections.3.list.0') }}</li>
      <li>{{ $t('terms.sections.3.list.1') }}</li>
      <li>{{ $t('terms.sections.3.list.2') }}</li>
    </ul>
    <p>{{ $t('terms.sections.3.content3') }}</p>

    <h2 class="h4">{{ $t('terms.sections.4.title') }}</h2>
    <p>{{ $t('terms.sections.4.content1') }}</p>
    <ul class="legalities-list">
      <li>{{ $t('terms.sections.4.list.0') }}</li>
      <li>{{ $t('terms.sections.4.list.1') }}</li>
      <li>{{ $t('terms.sections.4.list.2') }}</li>
      <li>{{ $t('terms.sections.4.list.3') }}</li>
      <li>{{ $t('terms.sections.4.list.4') }}</li>
    </ul>
    <p>{{ $t('terms.sections.4.content2') }}</p>

    <h2 class="h4">{{ $t('terms.sections.5.title') }}</h2>
    <p>{{ $t('terms.sections.5.content1') }}</p>
    <p>{{ $t('terms.sections.5.content2') }}</p>

    <h2 class="h4">{{ $t('terms.sections.6.title') }}</h2>
    <p>{{ $t('terms.sections.6.content1') }}</p>
    <ul class="legalities-list">
      <li>{{ $t('terms.sections.6.list.0') }}</li>
      <li>{{ $t('terms.sections.6.list.1') }}</li>
      <li>{{ $t('terms.sections.6.list.2') }}</li>
      <li>{{ $t('terms.sections.6.list.3') }}</li>
    </ul>
    <p>{{ $t('terms.sections.6.content2') }}</p>
    <p>{{ $t('terms.sections.6.content3') }}</p>

    <h2 class="h4">{{ $t('terms.sections.7.title') }}</h2>
    <p>{{ $t('terms.sections.7.content') }}</p>
    <p>
      {{ $t('terms.sections.7.prefix') }}
      <router-link :to="{ name: 'privacyPolicy' }" class="p-2 text-dark">{{ $t('privacyPolicy.title') }}
      </router-link>

      {{ $t('terms.sections.7.suffix') }}
    </p>

    <h2 class="h4">{{ $t('terms.sections.8.title') }}</h2>
    <p>{{ $t('terms.sections.8.content') }}</p>

    <h2 class="h4">{{ $t('terms.sections.9.title') }}</h2>
    <p>{{ $t('terms.sections.9.content') }}</p>

    <h2 class="h4">{{ $t('terms.sections.10.title') }}</h2>
    <p>{{ $t('terms.sections.10.content') }}</p>
  </main>
</template>
<style>
.legalities-list {
  list-style-type: initial;
  margin: 1rem 3rem;
}
</style>