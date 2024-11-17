<template>
    <!-- SEARCH OFFERS-->
    <section>
        <header class="mb-4 mb-lg-5">
            <h1 class="header">{{ $t('searchFindPlay') }}</h1>
            <h2 class="txt-subtile">
                {{ $t('browseJoinFind') }}
            </h2>
        </header>

        <div class="d-flex justify-content-center">
            <form class="d-lg-flex col-12 col-lg-6 align-items-center" role="search">
                <input class="form-control px-4 py-2 txt-body jm-form-search" type="search"
                    :placeholder="$t('searchPlaceholder')" aria-label="Search" />
                <RouterLink to="results"
                    class="btn px-4 btn-primary col-12 col-lg-auto ms-lg-5 mt-3 mt-lg-0 shadow justify-content-center">
                    {{ $t('search') }}</RouterLink>
            </form>
        </div>
    </section>
</template>