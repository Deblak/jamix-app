<script setup>

</script>
<template>
    <section class="d-lg-flex justify-content-center">
        <div class="p-4 col-lg-6 jm-card-border">
            <h1 class="header text-center">Inscription</h1>

            <div class="d-lg-flex justify-content-center">
                <form action="post" class="col-lg-9">

                    <div class="mb-3">
                        <label for="firstname" class="form-label fw-medium txt-body">Nom&nbsp;<span
                                class="text-danger">*</span></label>
                        <input name="firstname" type="text" class="form-control rounded-pill" id="firstname">
                        <div class="form-text">Max. 20 characters</div>
                    </div>

                    <div class="mb-4">
                        <label for="mail" class="form-label fw-medium txt-body">Mail&nbsp;<span
                                class="text-danger">*</span></label>
                        <input name="mail" type="email" class="form-control rounded-pill" id="mail">
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label fw-medium txt-body">Mot de passe&nbsp;<span
                                class="text-danger">*</span></label>
                        <input name="password" type="text" class="form-control rounded-pill" id="password">
                    </div>
                    <div class="mb-4">
                        <label for="confirm" class="form-label fw-medium txt-body">Confirmation mot de passe&nbsp;<span
                                class="text-danger">*</span></label>
                        <input name="confirm" type="text" class="form-control rounded-pill" id="confirm">
                    </div>

                    <div class="mb-3">
                        <label for="instrument" class="form-label fw-medium txt-body">Instrument joué&nbsp;<span
                                class="text-danger">*</span></label>
                        <input name="instrument" type="text" class="form-control rounded-pill" id="instrument">
                        <div class="form-text">Max. 30 characters</div>
                    </div>

                    <div class="text-center mt-4">
                        <a href="index.html" class="btn px-4 button-primary jm-shadow-box">Continuer</a>
                    </div>
                </form>
            </div>

        </div>
    </section>
    <div class="mb-5 d-lg-flex justify-content-center text-center">
        <p class="txt-body m-0">Déjà inscrit ?&nbsp;</p>
        <RouterLink to="/connection" class="txt-body-highlight">Se connecter</RouterLink>
    </div>
</template>