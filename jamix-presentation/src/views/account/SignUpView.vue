<script setup>
import apiClient from '@/services/axiosApi.js';
import { ref, computed } from 'vue';
import useVuelidate from '@vuelidate/core';
import { required, maxLength, minLength, email, sameAs } from '@vuelidate/validators';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import DOMPurify from 'dompurify';
import { useAppStore } from '@/stores/useAppStore.js';
import { useSwalFire } from '@/composables/useSwalFire.js';

const { swalError } = useSwalFire();
const store = useAppStore();
const router = useRouter();
const { t } = useI18n();
const showPassword1 = ref(false);
const showPassword2 = ref(false);

const formData = ref({
  username: '',
  email: '',
  password: '',
  confirmPassword: '',
  acceptedTerms: false
});

const rules = computed(() => ({
  username: { required, maxLength: maxLength(20), $lazy: true },
  email: { required, email, maxLength: maxLength(320), $lazy: true },
  password: {
    required,
    maxLength: maxLength(72),
    minLength: minLength(12),
    valid: function (value) {
      return /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[#?!@$%^&*-]).{12,}$/.test(value);
    },
    $lazy: true
  },
  confirmPassword: {
    required,
    sameAsPassword: sameAs(formData.value.password),
    $lazy: true
  },
  acceptedTerms: { sameAs: sameAs(() => true), $lazy: true }
}));

const v$ = useVuelidate(rules, formData);

function purifyInput(input) {
  return DOMPurify.sanitize(input);
}

const handleSubmit = () => {
  v$.value.$touch();
  if (v$.value.$error) {
    swalError(t('errorValidationTitle'), t('errorValidationMessage'));
  } else {
    send();
  }
};

const send = async () => {
  try {
    const purifyData = {
      username: purifyInput(formData.value.username.trim()),
      email: purifyInput(formData.value.email.trim()),
      password: purifyInput(formData.value.password.trim())
    };
    const response = await apiClient.post('/account/signup', purifyData);
    if (response.status === 201) {
      store.showToast(t('successAuth'));
      router.push({ name: 'login' });
    }
  } catch (error) {
    console.error('Error during submission:', error);
    swalError(t('errorSwalTitle'), t('errorUnexpectedMessage'));
  }
};
</script>
<template>
  <section class="d-lg-flex justify-content-center">
    <div class="p-4 col-lg-6 jm-card-border">
      <h1 class="title-1 text-center">{{ $t('inscription') }}</h1>
      <div class="d-lg-flex justify-content-center">
        <form @submit.prevent="handleSubmit" novalidate class="col-lg-9">
          <p class="mt-4 small">{{ $t('requireLegend') }}</p>

          <div class="mb-3">
            <label for="username" class="form-label fw-medium label-required">{{ $t('name') }}</label>
            <span v-if="v$.username.$error" class="text-danger">{{ $t('errorUsername') }}</span>
            <input type="text" id="username" v-model="formData.username" class="form-control">
          </div>

          <div class="mb-4">
            <label for="email" class="form-label fw-medium label-required">{{ $t('email') }}</label>
            <span v-if="v$.email.$error" class="text-danger">{{ $t('errorEmail') }}</span>
            <input type="email" v-model="formData.email" id="email" class="form-control">
          </div>

          <div class="mb-4">
            <label for="password" class="form-label fw-medium label-required">{{ $t('password') }}</label>
            <div class="form-text mt-0 mb-2">{{ $t('pwdRules') }}</div>
            <span v-if="v$.password.$error" class="text-danger">{{ $t('errorPassword') }}</span>
            <div class="input-group">
              <input :type="showPassword1 ? 'text' : 'password'" id="password" class="form-control rounded-start-pill"
                v-model="formData.password" />
              <button type="button" class="btn btn-outline-secondary rounded-end-pill"
                @click="showPassword1 = !showPassword1"
                :aria-label="showPassword1 ? $t('hidePassword') : $t('showPassword')">
                <i :class="showPassword1 ? 'bi bi-eye-slash' : 'bi bi-eye'" aria-hidden="true"></i>
              </button>
            </div>
          </div>

          <div class="mb-4">
            <label for="confirmPassword" class="form-label fw-medium label-required">{{ $t('passwordConfirm') }}</label>
            <span v-if="v$.confirmPassword.$error" class="text-danger">{{ $t('errorConfirmPassword') }}</span>
            <div class="input-group">
              <input :type="showPassword2 ? 'text' : 'password'" id="confirmPassword"
                class="form-control rounded-start-pill" v-model="formData.confirmPassword" />
              <button type="button" class="btn btn-outline-secondary rounded-end-pill"
                @click="showPassword2 = !showPassword2"
                :aria-label="showPassword2 ? $t('hidePassword') : $t('showPassword')">
                <i :class="showPassword2 ? 'bi bi-eye-slash' : 'bi bi-eye'" aria-hidden="true"></i>
              </button>
            </div>
          </div>
          <span v-if="v$.acceptedTerms.$error" style="color: red;">{{ $t('terms.error') }}</span>
          <label for="terms" class="form-label fw-medium label-required">
            <input id="terms" type="checkbox" v-model="formData.acceptedTerms" />
            {{ $t('terms.accepted') }}
            <router-link :to="{ name: 'termsOfUse' }" class="text-dark" target="_blank">{{
              $t('termsOfUse') }}
            </router-link>
          </label>
          <div class="text-center mt-4">
            <button type="submit" class="btn px-4 btn-jm-primary jm-shadow-box">
              {{ $t('signUp') }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </section>
  <div class="mb-5 text-center">
    <p class="txt-body m-0">{{ $t('alreadyRegistered') }}</p>
    <router-link :to="{ name: 'login' }" class="txt-body-highlight">{{ $t('login') }}</router-link>
  </div>
</template>