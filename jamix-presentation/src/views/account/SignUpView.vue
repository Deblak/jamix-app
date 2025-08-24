<script setup>
import apiClient from '@/services/axiosApi.js';
import { ref, computed, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import useVuelidate from '@vuelidate/core';
import { required, email, maxLength, minLength, sameAs } from '@vuelidate/validators';
import { swalSwitchError } from '@/services/swalFireService.js';
import { useAppStore } from '@/stores/useAppStore';
import DOMPurify from 'dompurify';
import { useRouter } from 'vue-router';

const { t } = useI18n();
const store = useAppStore();
const router = useRouter();

// Anti-spam
const honeypot = ref('');
const formOpenedAt = ref(0);
onMounted(() => { formOpenedAt.value = Date.now(); });

// Switch visibility password
const showPassword1 = ref(false);
const showPassword2 = ref(false);

const form = ref({
  username: '',
  email: '',
  password: '',
  confirmPassword: '',
  acceptedTerms: false
});

const rules = computed(() => ({
  username: { required, maxLength: maxLength(20) },
  email: { required, email, maxLength: maxLength(320) },
  password: {
    required,
    minLength: minLength(12),
    maxLength: maxLength(72),
    valid: (value) => /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[#?!@$%^&*-]).{12,}$/.test(value)
  },
  confirmPassword: { required, sameAsPassword: sameAs(form.value.password) },
  acceptedTerms: { sameAsTrue: sameAs(true) }
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
  if (elapsed < 3000) {
    await swalSwitchError({ response: { status: 400 } });
    return;
  }
  try {
    const payload = {
      username: purifyInput(form.value.username),
      email: purifyInput(form.value.email),
      password: purifyInput(form.value.password)
    };
    const response = await apiClient.post('/account/signup', payload);
    if (response.status === 201) {
      resetForm();
      store.showToast(t('successAuth'));
      router.push({ name: 'login' });
    } else {
      throw { response };
    }
  } catch (err) {
    await swalSwitchError(err);
  }
};

const resetForm = () => {
  form.value = { username: '', email: '', password: '', confirmPassword: '', acceptedTerms: false };
  v$.value.$reset();
};
</script>

<template>
  <section class="d-lg-flex justify-content-center">
    <div class="p-4 col-lg-6 jm-card-border">
      <h1 class="title-1 text-center">{{ t('inscription') }}</h1>
      <div class="d-lg-flex justify-content-center">
        <form @submit.prevent="handleSubmit" novalidate class="col-lg-9">
          <!-- Honeypot -->
          <div class="d-none" aria-hidden="true">
            <label for="company">Company</label>
            <input id="company" v-model="honeypot" type="text" tabindex="-1" autocomplete="off" />
          </div>
          <p class="mt-4 small">{{ t('requireLegend') }}</p>
          <div class="mb-3">
            <label for="username" class="form-label fw-medium label-required">{{ t('name') }}</label>
            <span v-if="v$.username.$error" class="text-alert small" role="alert" aria-live="assertive"
              id="username-error">
              <template v-if="v$.username.required.$invalid">{{ t('errorNameRequired') }}</template>
              <template v-else-if="v$.username.maxLength.$invalid">{{ t('errorNameMaxLength') }}</template>
            </span>
            <input id="username" v-model.trim="form.username" type="text" class="form-control" required maxlength="20"
              aria-required="true" :aria-invalid="v$.username.$error ? 'true' : 'false'"
              :aria-describedby="v$.username.$error ? 'username-error' : null" @blur="v$.username.$touch();" />
          </div>
          <div class="mb-3">
            <label for="email" class="form-label fw-medium label-required">{{ t('email') }}</label>
            <span v-if="v$.email.$error" class="text-alert small" role="alert" aria-live="assertive" id="email-error">
              <template v-if="v$.email.required.$invalid">{{ t('errorEmailRequired') }}</template>
              <template v-else-if="v$.email.email.$invalid">{{ t('errorEmailInvalid') }}</template>
              <template v-else-if="v$.email.maxLength.$invalid">{{ t('errorEmailMaxLength') }}</template>
            </span>
            <input id="email" v-model.trim="form.email" type="email" class="form-control" required maxlength="320"
              autocomplete="email" inputmode="email" aria-required="true"
              :aria-invalid="v$.email.$error ? 'true' : 'false'"
              :aria-describedby="v$.email.$error ? 'email-error' : null" @blur="v$.email.$touch();" />
          </div>
          <!-- Password -->
          <div class="mb-3">
            <label for="password" class="form-label fw-medium label-required">{{ t('password') }}</label>
            <div class="form-text mt-0 mb-2">{{ t('pwdRules') }}</div>
            <span v-if="v$.password.$error" class="text-alert small" role="alert" aria-live="assertive"
              id="password-error">
              <template v-if="v$.password.required.$invalid">{{ t('errorPasswordRequired') }}</template>
              <template v-else-if="v$.password.minLength.$invalid">{{ t('errorPasswordMinLength') }}</template>
              <template v-else-if="v$.password.maxLength.$invalid">{{ t('errorPasswordMaxLength') }}</template>
              <template v-else-if="!v$.password.valid">{{ t('errorPasswordInvalid') }}</template>
            </span>
            <div class="input-group">
              <input :type="showPassword1 ? 'text' : 'password'" id="password" class="form-control rounded-start-pill"
                v-model.trim="form.password" required maxlength="72" aria-required="true"
                :aria-invalid="v$.password.$error ? 'true' : 'false'"
                :aria-describedby="v$.password.$error ? 'password-error' : null" @blur="v$.password.$touch();" />
              <button type="button" class="btn btn-outline-secondary rounded-end-pill"
                @click="showPassword1 = !showPassword1"
                :aria-label="showPassword1 ? t('hidePassword') : t('showPassword')">
                <i :class="showPassword1 ? 'bi bi-eye-slash' : 'bi bi-eye'" aria-hidden="true"></i>
              </button>
            </div>
          </div>
          <!-- Confirm Password -->
          <div class="mb-3">
            <label for="confirmPassword" class="form-label fw-medium label-required">{{ t('passwordConfirm') }}</label>
            <span v-if="v$.confirmPassword.$error" class="text-alert small" role="alert" aria-live="assertive"
              id="confirmPassword-error">
              <template v-if="v$.confirmPassword.required.$invalid">{{ t('errorConfirmPasswordRequired') }}</template>
              <template v-else-if="v$.confirmPassword.sameAsPassword.$invalid">{{ t('errorConfirmPasswordMismatch')
              }}</template>
            </span>
            <div class="input-group">
              <input :type="showPassword2 ? 'text' : 'password'" id="confirmPassword"
                class="form-control rounded-start-pill" v-model.trim="form.confirmPassword" required
                aria-required="true" :aria-invalid="v$.confirmPassword.$error ? 'true' : 'false'"
                :aria-describedby="v$.confirmPassword.$error ? 'confirmPassword-error' : null"
                @blur="v$.confirmPassword.$touch();" />
              <button type="button" class="btn btn-outline-secondary rounded-end-pill"
                @click="showPassword2 = !showPassword2"
                :aria-label="showPassword2 ? t('hidePassword') : t('showPassword')">
                <i :class="showPassword2 ? 'bi bi-eye-slash' : 'bi bi-eye'" aria-hidden="true"></i>
              </button>
            </div>
          </div>
          <!-- Terms -->
          <div class="mb-3">
            <span v-if="v$.acceptedTerms.$error" class="text-alert small" role="alert" aria-live="assertive"
              id="terms-error">
              {{ t('terms.error') }}
            </span>
            <label for="terms" class="form-label fw-medium label-required">
              <input id="terms" type="checkbox" v-model="form.acceptedTerms"
                :aria-invalid="v$.acceptedTerms.$error ? 'true' : 'false'"
                :aria-describedby="v$.acceptedTerms.$error ? 'terms-error' : null" @blur="v$.acceptedTerms.$touch();" />
              {{ t('terms.accepted') }}
              <router-link :to="{ name: 'termsOfUse' }" target="_blank">{{ t('termsOfUse') }}</router-link>
            </label>
          </div>

          <div class="text-center mt-4">
            <button type="submit" class="btn px-4 btn-jm-primary jm-shadow-box">
              {{ t('signUp') }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </section>

  <div class="mb-5 text-center">
    <p class="txt-body m-0">{{ t('alreadyRegistered') }}</p>
    <router-link :to="{ name: 'login' }" class="txt-body-highlight">{{ t('login') }}</router-link>
  </div>
</template>