<script setup>
import { computed } from 'vue';
import { getOfferImageUrl } from '@/utils/imagePath';

const props = defineProps({
  id: Number,
  title: String,
  city: String,
  zipCode: String,
  instrument: String,
  style: String,
  goal: String,
  createdAt: String,
  imageUrl: String
});
const imagePath = computed(() => getOfferImageUrl(props.imageUrl));

</script>

<template>
  <div class="card custom-card mb-5">
    <img class="card-img-top" v-if="imagePath" :src="imagePath" :alt="`${title}`" />
    <div class="card-body">
      <h5 class="card-title title-2">{{ title }}</h5>
      <p class="txt-body text-primary m-0 fw-normal">{{ city }} - {{ zipCode }}</p>
      <ul class="card-txt txt-body">
        <li class="m-1 badge text-bg-danger fw-medium txt-small">{{ instrument }}</li>
        <li class="m-1 badge text-bg-warning fw-medium txt-small">{{ style }}</li>
        <li class="m-1 badge text-bg-primary text-white fw-medium txt-small">{{ goal }}</li>
      </ul>
      <p class="mt-3 mb-0 txt-body text-secondary ">{{ $formatDate(createdAt) }}</p>
    </div>
    <div class="card-footer txt-body-secondary bg-light">
      <router-link :to="{ name: 'detail', params: { id: id } }" class="btn px-4 btn-outline-primary">
        {{ $t('details') }}
      </router-link>
    </div>
  </div>
</template>

<style scope>
.custom-card {
  max-width: 320px;
}
</style>