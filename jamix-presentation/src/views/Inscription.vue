<script>
export default {
    data() {
        return {
            formData: {
                username: "",
                email: "",
                password: ""
            }
        }
    },
    methods: {
        async submit() {
            const options = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.formData)
            };
            const response = await fetch('http://localhost:8080/signup', options);
            if (response.ok) {
                alert('Compte créé avec succès');
            } else {
                console.error('Dev is a failure!');
            }
        },

    }
}
</script>
<template>
    <section class="d-lg-flex justify-content-center">
        <div class="p-4 col-lg-6 jm-card-border">
            <h1 class="header text-center">{{ $t('inscription') }}</h1>

            <div class="d-lg-flex justify-content-center">
                <form @submit.prevent="submit" novalidate class="col-lg-9">

                    <div class="mb-3">
                        <label for="username" class="form-label fw-medium txt-body">{{ $t('name') }}&nbsp;</label>
                        <input name="username" type="text" id="username" class="form-control rounded-pill"
                            v-model="formData.username">
                        <div class="form-text">{{ $t('max20Characters') }}</div>
                    </div>

                    <div class="mb-4">
                        <label for="email" class="form-label fw-medium txt-body">{{ $t('email') }}&nbsp;</label>
                        <input name="email" type="email" id="email" class="form-control rounded-pill"
                            v-model="formData.email">
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label fw-medium txt-body">{{ $t('password') }}&nbsp;
                        </label>
                        <input name="password" type="password" id="password" class="form-control rounded-pill"
                            v-model="formData.password">
                    </div>
                    <div class="mb-4">
                        <label for="confirm" class="form-label fw-medium txt-body">{{ $t('passwordConfirm')
                            }}&nbsp;</label>
                        <input name="confirm" type="password" id="confirm" class="form-control rounded-pill">
                    </div>

                    <!-- <div class="mb-3">
                        <label for="instrument" class="form-label fw-medium txt-body">{{ $t('instrumentPlayed')
                            }}&nbsp;<span class="text-danger">*</span></label>
                        <input name="instrument" type="text" class="form-control rounded-pill" id="instrument">
                        <div class="form-text">{{ $t('max30Characters') }}</div>
                    </div>-->

                    <button type="submit" class="mt-4 btn btn-primary jm-shadow-box justify-content-center">
                        {{ $t('signUp') }}
                    </button>
                </form>
            </div>

        </div>
    </section>
    <div class="mb-5 text-center">
        <p class="txt-body m-0">{{ $t('alreadyRegistered') }}</p>
        <RouterLink to="/connection" class="txt-body-highlight">{{ $t('logIn') }}</RouterLink>
    </div>
</template>

<style scoped>
label::after {
    content: '*';
    color: #f75d2e;
}
</style>