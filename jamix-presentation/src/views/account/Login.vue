<script setup>
import axios from 'axios';
import useVuelidate from '@vuelidate/core';
import { computed, ref } from 'vue';
import { required } from '@vuelidate/validators';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';

const formData = ref({
  email: '',
  password: ''
});

const rules = computed(() => {
  return {
    email: {
      required
    },
    password: {
      required
    }
  }
});

const v$ = useVuelidate(rules, formData);

const router = useRouter();
const { t } = useI18n();

const handleSubmit = () => {
  v$.value.$touch();
  if (!v$.value.$error) {
    send();
  } else {
    alert(t('errorValidation'));
  }
};

const send = async () => {
  try {
    const response = await axios.post('http://localhost:8080/account/login', formData.value);
    const token = response.data.token;
    if (token) {
      localStorage.setItem('jwt', token);
      alert(t('authentificated') + formData.value.email);
      router.push({ name: 'home' });
    } else {
      alert(t('errorCredentials'));
    }
  } catch (error) {
    console.error('Axios error:', error);
    alert(t('errorUnexpected') + (error.message || error));
  }
};
</script>
<template>
  <section class="d-lg-flex justify-content-center">
    <div class="p-4 col-lg-6 jm-card-border">
      <h1 class="title-1 text-center">{{ $t('connection') }}</h1>

      <div class="d-lg-flex justify-content-center">

        <form @submit.prevent="handleSubmit" novalidate class="col-lg-9">

          <div class="mb-4">
            <label for="email" class="form-label fw-medium txt-body">{{ $t('name') }}&nbsp;</label>
            <div v-if="v$.email.$error">
              <span class="text-danger">{{ $t('errorUsername') }}</span>
            </div>
            <input id="email" type="text" class="form-control rounded-pill" v-model="formData.email">
          </div>
          <div class="mb-4">
            <label for="password" class="form-label fw-medium txt-body">{{ $t('password') }}&nbsp;</label>

            <div v-if="v$.password.$error">
              <span class="text-danger">{{ $t('errorPassword') }}</span>
            </div>
            <input type="password" id="password" class="form-control rounded-pill" v-model="formData.password">
          </div>
          <!-- <a href="#" target="_blank" rel="noopener noreferrer" class="txt-body-highlight color-black">{{
            $t('passwordForgot')
          }}</a> -->
          <div class="text-center mt-4">
            <button type="submit" class="btn px-4 btn-primary jm-shadow-box">{{ $t('validate') }}</button>
          </div>
        </form>
      </div>

    </div>
  </section>
  <div class="mb-5 text-center">
    <p class="txt-body m-0">{{ $t('joinUs') }}</p>
    <RouterLink to="/signUp" class="txt-body-highlight">{{ $t('signUp') }}</RouterLink>
  </div>
</template>
<style scoped>
label::after {
  content: '*';
  color: #f75d2e;
}
</style>