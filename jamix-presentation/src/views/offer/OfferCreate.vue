<script setup>
import { computed, ref, onMounted } from 'vue';
import useVuelidate from '@vuelidate/core';
import { required, maxLength, minLength, email } from '@vuelidate/validators';
import apiClient from '../../services/axiosApi.js';
import { useI18n } from 'vue-i18n';

const createForm = ref({
    title: '',
    description: '',
    city: '',
    zipCode: '',
    picture: null,
    mail: '',
    instrumentId: null,
    styleId: null,
    goalId: null
});

const instruments = ref([]);
const styles = ref([]);
const goals = ref([]);


const fileType = {
    $validator(file) {
        if (file.length === 0) {
            return true;
        }
        const IMAGE_TAG_REGEX = /\.(jpg|jpeg)$/i;

        const MAX_SIZE = 2 * 1024 * 1024;

        return !(!IMAGE_TAG_REGEX.test(file.name) || file.size <= MAX_SIZE);
    }
};

const rules = computed(() => {
    return {
        title: {
            required,
            maxLength: maxLength(200)
        },
        description: {
            required,
            maxLength: maxLength(600)
        },
        city: {
            required,
            maxLength: maxLength(50)
        },
        zipCode: {
            required,
            maxLength: maxLength(5),
            minLength: minLength(5)
        },
        picture: {
            fileType
        },
        mail: {
            required,
            email,
            maxLength: maxLength(255)
        },
        instrumentId: required,
        styleId: required,
        goalId: required
    }
})

const v$ = useVuelidate(rules, createForm);
const { t } = useI18n();

const handleFileChange = (event) => {
    const file = event.target.files[0];
    createForm.value.picture = file;
};

const handleSubmit = () => {
    v$.value.$touch();
    if (!v$.value.$error) {
        send();
    } else {
        alert(t('errorValidation'));
    }
};

onMounted(async () => {
    try {
        const [instrumentsResponse, stylesResponse, goalsResponse] = await Promise.all([
            apiClient.get('/api/instruments'),
            apiClient.get('/api/styles'),
            apiClient.get('/api/goals')
        ]);

        instruments.value = instrumentsResponse.data;
        styles.value = stylesResponse.data;
        goals.value = goalsResponse.data;
    } catch (error) {
        console.error(t('errorLoadData'), error);
    }
});

const send = async () => {
    try {
        const response = await apiClient.post('/offers/create', createForm.value);
        if (response.status === 200) {
            createForm.value = {
                title: '', description: '', city: '', zipCode: '', mail: '',
                instrumentId: '', styleId: '', goalId: ''
            };
            v$.value.$reset();
            alert(t('successMessage'));
        } else {
            throw new Error(t('serverError'));
        }
    } catch (error) {
        if (error.response?.status === 401) {
            alert(t('errorSession'));
            localStorage.removeItem('jwt');
            window.location.href = '/login';
        } else {
            alert(t('errorUnexpected'));
            console.error(error);
        }
    }
};
</script>

<template>
    <section class="d-lg-flex flex-wrap justify-content-center">
        <h1 class="title-1 col-12">{{ $t('publish-new-offer') }}</h1>
        <div class="p-4 col-lg-6 jm-card-border bg-light">
            <small><span class="text-danger">*</span> {{ $t('required-fields') }}</small>
            <form id="create-form" @submit.prevent="handleSubmit">
                <!--title-->
                <div class="mt-4 mb-2">
                    <label class="form-label fw-medium label-required" for="title">{{ $t('offer-title') }}</label>

                    <div v-if="v$.title.$error">
                        <span class="text-danger">{{ $t('error-title') }}</span>
                    </div>

                    <input type="text" id="title" v-model="createForm.title" class="form-control"
                        :placeholder="$t('offer-title-placeholder')">
                </div>
                <!--city and zipCode-->
                <div class="row g-3 my-3">
                    <div class="col-md-6">
                        <label for="city" class="form-label fw-medium label-required">{{ $t('city') }}</label>
                        <div v-if="v$.city.$error">
                            <span class="text-danger">{{ $t('error-city') }}</span>
                        </div>
                        <input type="text" v-model="createForm.city" id="city" class="form-control"
                            :placeholder="$t('city-placeholder')">
                    </div>
                    <div class="col-md-6">
                        <label for="zipCode" class="form-label fw-medium label-required">{{ $t('zip-code') }}</label>
                        <div v-if="v$.zipCode.$error">
                            <span class="text-danger">{{ $t('error-zipCode') }}</span>
                        </div>
                        <input type="text" v-model="createForm.zipCode" id="zipCode" class="form-control"
                            :placeholder="$t('zip-code-placeholder')">
                    </div>
                </div>
                <!--picture-->
                <div class="my-3">
                    <label for="picture" class="form-label fw-medium">{{ $t('picture') }}</label>
                    <div v-if="v$.picture.$error">
                        <span class="text-danger">{{ $t('error-picture') }}</span>
                    </div>
                    <input type="file" id="picture" @change="handleFileChange" class="form-control">
                </div>
                <!--choices-->
                <div class="row g-3 my-3">
                    <span class="fw-medium">{{ $t('compose-your-ad') }}</span>
                    <div class="col-md-4 mt-2">
                        <label for="instrument-select"
                            class="form-label badge rounded-pill text-bg-primary fw-medium txt-small">
                            {{ $t('instrument') }}
                        </label>
                        <select v-model="createForm.instrumentId" id="instrument-select"
                            class="form-select form-select mb-3">
                            <option disabled value="">{{ $t('choose') }}</option>
                            <option v-for="instrument in instruments" :key="instrument.name" :value="instrument.id">
                                {{ instrument.name }}
                            </option>
                        </select>
                    </div>

                    <div class="col-md-4 mt-2">
                        <label for="style-select"
                            class="form-label badge rounded-pill text-bg-warning fw-medium txt-small">
                            {{ $t('style') }}
                        </label>
                        <select v-model="createForm.styleId" id="style-select" class="form-select form-select mb-3">
                            <option disabled value="">{{ $t('choose') }}</option>
                            <option v-for="style in styles" :key="style.name" :value="style.id">
                                {{ style.name }}
                            </option>
                        </select>
                    </div>

                    <div class="col-md-4 mt-2">
                        <label for="goal-select"
                            class="form-label badge rounded-pill text-bg-danger fw-medium txt-small">
                            {{ $t('goal') }}
                        </label>
                        <select v-model="createForm.goalId" id="goal-select" class="form-select form-select mb-3">
                            <option disabled value="">{{ $t('choose') }}</option>
                            <option v-for="goal in goals" :key="goal.type" :value="goal.id">
                                {{ goal.type }}
                            </option>
                        </select>
                    </div>
                </div>

                <div class="my-3">
                    <label for="description" class="form-label fw-medium label-required">{{ $t('description') }}</label>
                    <div v-if="v$.description.$error">
                        <span class="text-danger">{{ $t('error-description') }}</span>
                    </div>
                    <textarea type="text" v-model="createForm.description" id="description"
                        class="form-control px-3 py-2" rows="3"></textarea>
                </div>

                <div class=" mt-2">
                    <label for="mail" class="form-label fw-medium label-required">{{ $t('contact-email') }}</label>
                    <div v-if="v$.mail.$error">
                        <span class="text-danger">{{ $t('error-mail') }}</span>
                    </div>
                    <div class="input-group">
                        <span class="input-group-text" id="basic-addon1"><i class="bi bi-envelope-at"></i></span>
                        <input type="text" v-model="createForm.mail" id="mail" class="form-control"
                            :placeholder="$t('contact-email-placeholder')" aria-label="Mail"
                            aria-describedby="basic-addon1">
                    </div>
                </div>

                <div class="mt-5">
                    <!-- <button class="btn px-4 btn-outline-primary me-md-4 jm-shadow-box">{{ $t('preview') }}</button> -->
                    <button type="submit" class="btn px-4 btn-primary jm-shadow-box">{{ $t('save') }}</button>
                </div>
            </form>
        </div>
    </section>
</template>
<style scoped>
.label-required::after {
    content: ' *';
    color: #f75d2e;
}
</style>