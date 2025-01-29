<script setup>

defineProps({
  id: Number,
  title: String,
  city: String,
  zipCode: String,
  instrument: String,
  style: String,
  goal: String,
  createdAt: String
});

</script>

<template>
  <div class="card custom-card mb-5">
    <img src="../assets/pictures/elizeu-dias-29QO6oX3GlA-unsplash.jpg" class="card-img-top" alt="Offer picture">
    <div class="card-body">
      <h5 class="card-title title-2">{{ title }}</h5>
      <p class="txt-body color-primary m-0">{{ city }} - {{ zipCode }}</p>
      <ul class="card-txt txt-body">
        <li class="m-1 badge text-bg-danger fw-medium txt-small rounded-pill">{{ instrument }}</li>
        <li class="m-1 badge text-bg-warning fw-medium txt-small rounded-pill">{{ style }}</li>
        <li class="m-1 badge text-bg-secondary fw-medium text-wrap rounded-pill">{{ goal }}</li>
      </ul>
      <p class="mt-3 mb-0 txt-body color-soft">{{ $formatDate(createdAt) }}</p>
    </div>
    <div class="card-footer txt-body-secondary bg-light">
      <RouterLink :to="{ name: 'detail', params: { id: id } }" class="btn px-4 btn-outline-primary">
        {{ $t('details') }}
      </RouterLink>
    </div>
  </div>
</template>

<style scope>
.text-bg-secondary {
  color: white !important;
  background-color: #bd9cde !important;
}

.custom-card {
  max-width: 320px;
}
</style>