<script setup>
import apiClient from '@/services/axiosApi';
import useVuelidate from '@vuelidate/core';
import { computed, ref, inject } from 'vue';
import { required } from '@vuelidate/validators';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import DOMPurify from 'dompurify';
import { useAppStore } from '@/stores/useAppStore';
import { useSwalFire } from '@/composables/useSwalFire';
const { swalError } = useSwalFire();

const store = useAppStore();
const showPassword = ref(false);

function purifyInput(input) {
  return DOMPurify.sanitize(input);
};

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

const auth = inject('auth');
const router = useRouter();
const { t } = useI18n();

const handleSubmit = () => {
  v$.value.$touch();
  if (v$.value.$invalid) {
    swalError(t('errorValidationTitle'), t('errorValidationMessage'));
  } else {
    send();
  }
};

const send = async () => {
  try {
    const purifyData = {
      email: purifyInput(formData.value.email.trim()),
      password: purifyInput(formData.value.password.trim())
    };

    const response = await apiClient.post('/account/login', purifyData);
    const token = response.data.token;
    if (token) {
      localStorage.setItem('jwt', token);
      store.showToast(
        t('authentificated', { email: purifyData.email }),
        t('screenReaderHomeRedirect')
      );
      auth.login(token);
      router.push({ name: 'home' });
    }
  } catch (error) {
    console.error('Axios error:', error);
    if (error.response?.status === 401) {
      swalError(t('errorUnauthorizedTitle'), t('errorUnauthorizedMessage'));
    } else {
      swalError(t('errorUnexpectedTitle'), t('errorUnexpectedMessage'));
    }
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
            <label for="email" class="form-label fw-medium">{{ $t('email') }}</label>
            <span v-if="v$.email.$error" class="text-danger" id="error-email">
              {{ $t('requiredField') }}
            </span>
            <input id="email" type="text" class="form-control rounded-pill" v-model="formData.email"
              @blur="v$.email.$touch" :aria-describedby="v$.email.$error ? 'error-email' : null"
              :aria-invalid="v$.email.$error ? 'true' : 'false'" />
          </div>
          <div class="mb-4">
            <label for="password" class="form-label fw-medium">{{ $t('password') }}</label>
            <span v-if="v$.password.$error" class="text-danger" id="error-password">
              {{ $t('requiredField') }}
            </span>
            <div class="input-group">
              <input :type="showPassword ? 'text' : 'password'" id="password" class="form-control rounded-start-pill"
                v-model="formData.password" @blur="v$.password.$touch"
                :aria-describedby="v$.password.$error ? 'error-password' : null"
                :aria-invalid="v$.password.$error ? 'true' : 'false'" />
              <button type="button" class="btn btn-outline-secondary rounded-end-pill"
                @click="showPassword = !showPassword"
                :aria-label="showPassword ? $t('hidePassword') : $t('showPassword')">
                <i :class="showPassword ? 'bi bi-eye-slash' : 'bi bi-eye'" aria-hidden="true"></i>
              </button>
            </div>
          </div>
          <div class="text-center mt-4">
            <button type="submit" class="btn px-4 btn-primary jm-shadow-box">
              {{ $t('validate') }}
            </button>
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
  content: '* ';
  color: #f75d2e;
}

button:focus-visible {
  outline: 2px solid black;
  outline-offset: 2px;
}
</style>