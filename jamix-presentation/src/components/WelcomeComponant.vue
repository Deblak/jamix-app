<template>
    <!-- SEARCH OFFERS-->
    <section>
        <header class="mb-4 mb-lg-5">
            <h1 class="header">Cherchez, trouvez, jouez</h1>
            <h2 class="txt-subtile">
                Parcourez les annonces, rejoignez un groupe, trouvez la bande idéale
            </h2>
        </header>

        <div class="d-flex justify-content-center">
            <form class="d-lg-flex col-12 col-lg-6 align-items-center" role="search">
                <input class="form-control px-4 py-2 txt-body jm-form-search" type="search"
                    placeholder="Guitare, chanteur, ..." aria-label="Search" />
                <RouterLink to="results"
                    class="btn px-4 button-primary col-12 col-lg-auto ms-lg-5 mt-3 mt-lg-0 shadow justify-content-center">
                    Rechercher</RouterLink>
            </form>
        </div>
    </section>


</template>