<script setup>
import apiClient from '@/services/axiosApi.js';
import { useAuth } from '@/stores/useAuthStore';
import { ref, computed, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import useVuelidate from '@vuelidate/core';
import { required, email, minLength, maxLength } from '@vuelidate/validators';
import { swalSwitchError } from '@/services/swalFireService.js';
import DOMPurify from 'dompurify';
import { useRouter } from 'vue-router';
import { useAppStore } from '@/stores/useAppStore';

const { t } = useI18n();
const store = useAppStore();
const auth = useAuth();
const router = useRouter();

// Anti-spam
const honeypot = ref('');
const formOpenedAt = ref(0);
onMounted(() => { formOpenedAt.value = Date.now(); });

const showPassword = ref(false);

const form = ref({
  email: '',
  password: ''
});

const rules = computed(() => ({
  email: { required, email, minLength: minLength(6), maxLength: maxLength(320) },
  password: { required, minLength: minLength(12), maxLength: maxLength(72) }
}));
const v$ = useVuelidate(rules, form);

const purifyInput = (input) => DOMPurify.sanitize(input.trim());

const handleSubmit = async () => {
  v$.value.$touch();
  if (!v$.value.$error) {
    await send();
  } else {
    await swalSwitchError({ response: { status: 422 } });
  }
};

const send = async () => {
  if (honeypot.value) return;
  const elapsed = Date.now() - formOpenedAt.value;
  if (elapsed < 1500) {
    await swalSwitchError({ response: { status: 400 } });
    return;
  }
  try {
    const payload = {
      email: purifyInput(form.value.email),
      password: purifyInput(form.value.password)
    };
    const response = await apiClient.post('/account/login', payload);
    const token = response.data.token;
    if (token) {
      localStorage.setItem('jwt', token);
      auth.login(token);
      store.showToast(t('authentificated', { username: auth.username }), t('screenReaderHomeRedirect'));
      router.push({ name: 'home' });
    } else {
      throw { response: { status: 401 } };
    }
  } catch (err) {
    await swalSwitchError(err);
  }
};
</script>

<template>
  <section class="d-lg-flex justify-content-center">
    <div class="p-4 col-lg-6 jm-card-border">
      <h1 class="title-1 text-center">{{ t('connection') }}</h1>
      <div class="d-lg-flex justify-content-center">
        <form @submit.prevent="handleSubmit" novalidate class="col-lg-9">
          <div class="d-none" aria-hidden="true">
            <label for="company">Company</label>
            <input id="company" v-model="honeypot" type="text" tabindex="-1" autocomplete="off" />
          </div>
          <p class="my-4 small">{{ t('requireLegend') }}</p>
          <div class="mb-4">
            <label for="email" class="form-label fw-medium label-required">{{ t('email') }}</label>
            <span v-if="v$.email.$error" class="text-alert small" role="alert" aria-live="assertive" id="email-error">
              <template v-if="v$.email.required.$invalid">{{ t('errorEmailRequired') }}</template>
              <template v-else-if="v$.email.email.$invalid">{{ t('errorEmailInvalid') }}</template>
              <template v-else-if="v$.email.minLength.$invalid">{{ t('errorEmailMinLength') }}</template>
              <template v-else-if="v$.email.maxLength.$invalid">{{ t('errorEmailMaxLength') }}</template>
            </span>
            <input id="email" v-model.trim="form.email" type="email" class="form-control" required maxlength="320"
              autocomplete="email" inputmode="email" aria-required="true"
              :aria-invalid="v$.email.$error ? 'true' : 'false'"
              :aria-describedby="v$.email.$error ? 'email-error' : null" @blur="v$.email.$touch();" />
          </div>

          <!-- Password -->
          <div class="mb-4">
            <label for="password" class="form-label fw-medium label-required">{{ t('password') }}</label>
            <span v-if="v$.password.$error" class="text-alert small" role="alert" aria-live="assertive"
              id="password-error">
              <template v-if="v$.password.required.$invalid">{{ t('errorPasswordRequired') }}</template>
              <template v-else-if="v$.password.minLength.$invalid">{{ t('errorPasswordMinLength') }}</template>
              <template v-else-if="v$.password.maxLength.$invalid">{{ t('errorPasswordMaxLength') }}</template>
            </span>
            <div class="input-group">
              <input :type="showPassword ? 'text' : 'password'" id="password" class="form-control rounded-start-pill"
                v-model.trim="form.password" required maxlength="72" aria-required="true"
                :aria-invalid="v$.password.$error ? 'true' : 'false'"
                :aria-describedby="v$.password.$error ? 'password-error' : null" @blur="v$.password.$touch();" />
              <button type="button" class="btn btn-outline-secondary rounded-end-pill"
                @click="showPassword = !showPassword"
                :aria-label="showPassword ? t('hidePassword') : t('showPassword')">
                <i :class="showPassword ? 'bi bi-eye-slash' : 'bi bi-eye'" aria-hidden="true"></i>
              </button>
            </div>
          </div>
          <div class="text-center mt-4">
            <button type="submit" class="btn px-4 btn-jm-primary jm-shadow-box">
              {{ t('validate') }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </section>
  <div class="mb-5 text-center">
    <p class="txt-body m-0">{{ t('joinUs') }}</p>
    <router-link :to="{ name: 'signUp' }" class="txt-body-highlight">{{ t('signUp') }}</router-link>
  </div>
</template>
