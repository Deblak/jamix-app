<script>
import apiClient from '../../services/axiosApi.js';

export default {
  data() {
    return {
      formData: {
        username: "",
        password: ""
      }
    }
  },
  methods: {
    // async submit() {
    //   const options = {
    //     method: 'POST',
    //     headers: {
    //       'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify(this.formData)
    //   };
    //   const response = await fetch('http://localhost:8080/account/login', options);
    //   if (response.ok) {
    //     alert('Account authenticated with username: ' + this.formData.username);
    //   } else if (response.status == 401) {
    //     alert("Bad credentials");
    //   }
    //   else {
    //     alert('Account not found with username: ' + this.formData.username);
    //   }
    // }


    async submit() {
      try {
        const response = await apiClient.post('/account/login', this.formData);
        const token = response.data.token;
        localStorage.setItem('jwt', token);
        alert('Account authenticated with username: ' + this.formData.username);

      } catch (error) {
        if (error.response && error.response.status === 401) {
          alert('Bad credentials');
        } else {
          alert('An error occurred: ' + error.message);
        }
      }
    }

  }
}
</script>
<template>
  <section class="d-lg-flex justify-content-center">
    <div class="p-4 col-lg-6 jm-card-border">
      <h1 class="title-1 text-center">{{ $t('connection') }}</h1>

      <div class="d-lg-flex justify-content-center">

        <form @submit.prevent="submit" novalidate class="col-lg-9">

          <div class="mb-4">
            <label for="username" class="form-label fw-medium txt-body">{{ $t('email') }}&nbsp;</label>
            <input name="username" id="username" type="text" class="form-control rounded-pill"
              v-model="formData.username">
          </div>
          <div class="mb-4">
            <label for="password" class="form-label fw-medium txt-body">{{ $t('password') }}&nbsp;</label>
            <input name="password" type="password" id="password" class="form-control rounded-pill"
              v-model="formData.password">
          </div>
          <a href="#" target="_blank" rel="noopener noreferrer" class="txt-body-highlight color-black">{{
            $t('passwordForgot')
          }}</a>
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