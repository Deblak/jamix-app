<script setup>

</script>
<template>
  <section class="d-lg-flex justify-content-center">
    <div class="p-4 col-lg-6 jm-card-border">
      <h1 class="header text-center">Connexion</h1>

      <div class="d-lg-flex justify-content-center">

        <form action="post" class="col-lg-9">

          <div class="mb-4">
            <label for="mail" class="form-label fw-medium txt-body">Mail&nbsp;<span class="text-danger">*</span></label>
            <input name="mail" type="email" class="form-control rounded-pill" id="mail">
          </div>
          <div class="mb-4">
            <label for="password" class="form-label fw-medium txt-body">Mot de passe&nbsp;<span
                class="text-danger">*</span></label>
            <input name="password" type="text" class="form-control rounded-pill" id="password">
          </div>
          <a href="#" target="_blank" rel="noopener noreferrer" class="txt-body-highlight color-black">Mot de passe
            oublié</a>
          <div class="text-center mt-4">
            <a href="index.html" class="btn px-4 btn-primary jm-shadow-box">Valider</a>
          </div>
        </form>
      </div>

    </div>
  </section>
  <div class="mb-5 d-lg-flex justify-content-center text-center">
    <p class="txt-body m-0">Envie de nous rejoindre ?&nbsp;</p>
    <RouterLink to="/inscription" class="txt-body-highlight">Créer un compte</RouterLink>
  </div>
</template>