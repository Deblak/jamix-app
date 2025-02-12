<script setup>
import { computed, reactive, onMounted } from 'vue';
import useVuelidate from '@vuelidate/core';
import { required, maxLength, minLength, email } from '@vuelidate/validators';
import apiClient from '../../services/axiosApi.js';
import { useI18n } from 'vue-i18n';

const createForm = reactive({
    title: '',
    description: '',
    city: '',
    zipCode: '',
    contactMail: '',
    instrumentId: null,
    styleId: null,
    goalId: null,
    image: null
});

const instruments = reactive([]);
const styles = reactive([]);
const goals = reactive([]);


const fileRules = {
    $validator(file) {
        if (!file) return true;

        const IMAGE_TAG_REGEX = /\.(jpg|jpeg)$/i;

        const MAX_SIZE = 2 * 1024 * 1024;

        return IMAGE_TAG_REGEX.test(file.name) && file.size <= MAX_SIZE;
    }
};

const rules = computed(() => {
    return {
        title: {
            required, maxLength: maxLength(200), $lazy: true
        },
        city: {
            required, maxLength: maxLength(50), $lazy: true
        },
        zipCode: {
            required, maxLength: maxLength(5), minLength: minLength(5), $lazy: true
        },
        image: {
            fileRules, $lazy: true
        },
        instrumentId: { required, $lazy: true },
        styleId: { required, $lazy: true },
        goalId: { required, $lazy: true },
        description: {
            required, maxLength: maxLength(600), $lazy: true
        },
        contactMail: {
            required, email, maxLength: maxLength(255), $lazy: true
        }
    }
})
const v$ = useVuelidate(rules, createForm);

const { t } = useI18n();
const handleImageUpload = (event) => {
    const file = event.target.files[0];
    if (file) {
        createForm.image = file;
    }
};
const handleSubmit = () => {
    v$.value.$touch();
    if (!v$.value.$invalid) {
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

        Object.assign(instruments, instrumentsResponse.data);
        Object.assign(styles, stylesResponse.data);
        Object.assign(goals, goalsResponse.data);
    } catch (error) {
        console.error(t('errorLoadData'), error);
    }
});

const resetForm = () => {
    Object.assign(createForm, {
        title: '',
        description: '',
        city: '',
        zipCode: '',
        contactMail: '',
        instrumentId: null,
        styleId: null,
        goalId: null,
        image: ''
    });

    v$.value.$reset();
};

const send = async () => {
    try {
        const formData = new FormData();
        Object.entries(createForm).forEach(([key, value]) => {
            if (value !== null && value !== '') {
                formData.append(key, value);
            }
        });

        if (createForm.image) {
            formData.append('image', createForm.image);
        }

        const response = await apiClient.post('/offers/create', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });

        if (response.status === 200) {
            resetForm();
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
        <h1 class="title-1 col-12">{{ $t('publishNewOffer') }}</h1>
        <div class="p-4 col-lg-6 jm-card-border bg-light">
            <small><span class="text-danger">*</span> {{ $t('requiredFields') }}</small>
            <form id="create-form" @submit.prevent="handleSubmit">
                <!--title-->
                <div class="mt-4 mb-2">
                    <label class="form-label fw-medium label-required" for="title">{{ $t('offerTitle') }}</label>

                    <div v-if="v$.title.$invalid">
                        <span class="text-danger">{{ $t('errorTitle') }}</span>
                    </div>

                    <input type="text" id="title" v-model="createForm.title" class="form-control"
                        :placeholder="$t('offerTitlePlaceholder')">
                </div>
                <!--city and zipCode-->
                <div class="row g-3 my-3">
                    <div class="col-md-6">
                        <label for="city" class="form-label fw-medium label-required">{{ $t('city') }}</label>
                        <div v-if="v$.city.$invalid">
                            <span class="text-danger">{{ $t('errorCity') }}</span>
                        </div>
                        <input type="text" v-model="createForm.city" id="city" class="form-control"
                            :placeholder="$t('cityPlaceholder')">
                    </div>
                    <div class="col-md-6">
                        <label for="zipCode" class="form-label fw-medium label-required">{{ $t('zipCode') }}</label>
                        <div v-if="v$.zipCode.$invalid">
                            <span class="text-danger">{{ $t('errorZipCode') }}</span>
                        </div>
                        <input type="text" v-model="createForm.zipCode" @blur="v$.zipCode.$touch" id="zipCode"
                            class="form-control" :placeholder="$t('zipCodePlaceholder')">
                    </div>
                </div>
                <!--picture-->
                <div class="my-3">
                    <label for="image" class="form-label fw-medium">{{ $t('picture') }}</label>
                    <div v-if="v$.image.$invalid">
                        <span class="text-danger">{{ $t('errorPicture') }}</span>
                    </div>
                    <input type="file" id="image" class="form-control" accept="image/jpeg" @change="handleImageUpload"
                        placeholder="2Mo max, Jpeg">
                </div>
                <!--choices-->
                <div class="row g-3 my-3">
                    <span class="fw-medium">{{ $t('composeYourAd') }}</span>
                    <div class="col-md-4 mt-2">
                        <label for="instrument-select"
                            class="form-label badge rounded-pill text-bg-primary fw-medium txt-small">
                            {{ $t('instrument') }}
                        </label>
                        <div v-if="v$.instrumentId.$invalid">
                            <span class="text-danger">{{ $t('errorSelect') }}</span>
                        </div>
                        <select v-model="createForm.instrumentId" id="instrument-select"
                            class="form-select form-select mb-3">
                            <option disabled value="">{{ $t('choose') }}</option>
                            <option v-for="instrument in instruments" :key="instrument.id" :value="instrument.id">
                                {{ instrument.name }}
                            </option>
                        </select>
                    </div>

                    <div class="col-md-4 mt-2">
                        <label for="style-select"
                            class="form-label badge rounded-pill text-bg-warning fw-medium txt-small">
                            {{ $t('style') }}
                        </label>
                        <div v-if="v$.styleId.$invalid">
                            <span class="text-danger">{{ $t('errorSelect') }}</span>
                        </div>
                        <select v-model="createForm.styleId" id="style-select" class="form-select form-select mb-3">
                            <option disabled value="">{{ $t('choose') }}</option>
                            <option v-for="style in styles" :key="style.id" :value="style.id">
                                {{ style.name }}
                            </option>
                        </select>
                    </div>

                    <div class="col-md-4 mt-2">
                        <label for="goal-select"
                            class="form-label badge rounded-pill text-bg-danger fw-medium txt-small">
                            {{ $t('goal') }}
                        </label>
                        <div v-if="v$.goalId.$invalid">
                            <span class="text-danger">{{ $t('errorSelect') }}</span>
                        </div>
                        <select v-model="createForm.goalId" id="goal-select" class="form-select form-select mb-3">
                            <option disabled value="">{{ $t('choose') }}</option>
                            <option v-for="goal in goals" :key="goal.id" :value="goal.id">
                                {{ goal.type }}
                            </option>
                        </select>
                    </div>
                </div>

                <div class="my-3">
                    <label for="description" class="form-label fw-medium label-required">{{ $t('description') }}</label>
                    <div v-if="v$.description.$invalid">
                        <span class="text-danger">{{ $t('errorDescription') }}</span>
                    </div>
                    <textarea type="text" v-model="createForm.description" id="description"
                        :placeholder="$t('descriptionPlaceholder')" class="form-control px-3 py-2" rows="3"></textarea>
                </div>

                <div class=" mt-2">
                    <label for="contactMail" class="form-label fw-medium label-required">{{ $t('contactEmail')
                        }}</label>
                    <div v-if="v$.contactMail.$invalid">
                        <span class="text-danger">{{ $t('errorMail') }}</span>
                    </div>
                    <div class="input-group">
                        <span class="input-group-text" id="basic-addon1"><i class="bi bi-envelope-at"></i></span>
                        <input type="text" v-model="createForm.contactMail" id="contactMail" class="form-control"
                            :placeholder="$t('contactEmailPlaceholder')" aria-label="Mail"
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