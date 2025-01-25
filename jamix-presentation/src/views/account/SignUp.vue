<script setup>
import axios from 'axios';
import { ref, computed } from 'vue';
import useVuelidate from '@vuelidate/core';
import { required, maxLength, minLength, email, sameAs } from '@vuelidate/validators';
import { useRouter } from 'vue-router';

const formData = ref({
  username: '',
  email: '',
  password: '',
  confirmPassword: ''
});

const rules = computed(() => ({
  username: { required, maxLength: maxLength(20) },
  email: { required, email, maxLength: maxLength(320) },
  password: { 
    required, 
    maxLength: maxLength(72),  
    minLength: minLength(8),
    valid: function(value) {
    //   const containsUppercase = /[A-Z]/.test(value)
    //   const containsLowercase = /[a-z]/.test(value)
    //   const containsNumber = /[0-9]/.test(value)
    //   const containsSpecial = /[#?!@$%^&*-]/.test(value)
    //   return containsUppercase && containsLowercase && containsNumber && containsSpecial
    return /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[#?!@$%^&*-]).{8,}$/.test(value);
    }
    },
  confirmPassword: {
    required, 
    sameAsPassword: sameAs(formData.value.password)
   //sameAsPassword: sameAs(computed(() => formData.value.password))
}
}));

const v$ = useVuelidate(rules, formData);

const router = useRouter();

const submit = () => {
  v$.value.$touch();
  if (!v$.value.$error) {
    send();
  } else {
    alert('Validation errors, please check your inputs!');
  }
};
const send = async () => {
    try {
      const response = await axios.post('http://localhost:8080/account/signup', formData.value);
      if (response.status === 201) {
        alert('Compte créé avec succès');
        router.push({ name: 'login' });
      } else {
        console.error('Erreur lors de la création du compte');
      }
    } catch (error) {
      console.error('Erreur lors de la soumission :', error);
    }
};
</script>
<template>
    <section class="d-lg-flex justify-content-center">
        <div class="p-4 col-lg-6 jm-card-border">
            <h1 class="title-1 text-center">{{ $t('inscription') }}</h1>

            <div class="d-lg-flex justify-content-center">
                <form @submit.prevent="submit" novalidate class="col-lg-9">

                    <div class="mb-3">
                        <label for="username" class="form-label fw-medium txt-body">{{ $t('name') }}&nbsp;</label>
                        <div v-if="v$.username.$error">
                            <span class="text-danger">{{ $t('error-username') }}</span>
                        </div>
                        <input type="text" id="username" v-model="formData.username" 
                        class="form-control rounded-pill">
                    </div>

                    <div class="mb-4">
                        <label for="email" class="form-label fw-medium txt-body">{{ $t('email') }}&nbsp;</label>
                        <div v-if="v$.email.$error">
                            <span class="text-danger">{{ $t('error-mail') }}</span>
                        </div>
                        <input type="email" v-model="formData.email" id="email" 
                        class="form-control rounded-pill">
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label fw-medium txt-body mb-0">{{ $t('password') }}&nbsp;
                        </label>
                        <div class="form-text mt-0 mb-2">{{ $t('pwdRules') }}</div>

                        <div v-if="v$.password.$error">
                            <span class="text-danger">{{ $t('error-password') }}</span>
                        </div>
                        <input type="password" id="password" v-model="formData.password" 
                        class="form-control rounded-pill">
                    </div>

                    <div class="mb-4">
                        <label for="confirmPassword" class="form-label fw-medium txt-body">{{ $t('passwordConfirm')
                        }}&nbsp;</label>

                        <div v-if="v$.confirmPassword.$error">
                            <span class="text-danger">{{ $t('error-confirmPassword') }}</span>
                        </div>
                        
                        <input type="password" id="confirmPassword" v-model="formData.confirmPassword" 
                        class="form-control rounded-pill">
                    </div>

                    <button type="submit" class="mt-4 btn btn-primary jm-shadow-box justify-content-center">
                        {{ $t('signUp') }}
                    </button>
                </form>
            </div>

        </div>
    </section>
    <div class="mb-5 text-center">
        <p class="txt-body m-0">{{ $t('alreadyRegistered') }}</p>
        <RouterLink to="/login" class="txt-body-highlight">{{ $t('login') }}</RouterLink>
    </div>
</template>

<style scoped>
label::after {
    content: '*';
    color: #f75d2e;
}
</style>