<script setup>
import { computed, reactive, watch, onMounted, ref } from 'vue';
import useVuelidate from '@vuelidate/core';
import { required, maxLength, minLength, email } from '@vuelidate/validators';
import { useI18n } from 'vue-i18n';
import DOMPurify from 'dompurify';
import apiClient from '@/services/axiosApi.js';
import SelectPreference from '@/components/SelectPreference.vue';
import { useLocationService } from '@/services/locationService.js';
const { isCityAndZipValid } = useLocationService();

const props = defineProps({
    mode: { type: String, default: 'create' },
    initialData: { type: Object, default: () => ({}) },
});

const emit = defineEmits(['success', 'error'])

const { t } = useI18n();

const form = reactive({
    title: '',
    description: '',
    city: '',
    zipCode: '',
    contactMail: '',
    instrumentId: null,
    styleId: null,
    goalId: null,
    image: null,
});

const instruments = ref([]);
const styles = ref([]);
const goals = ref([]);

watch(
    () => props.initialData,
    (newData) => {
        if (props.mode === 'edit' && newData) {
            Object.assign(form, newData);
        }
    },
    { immediate: true }
);

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

const fileRules = {
    $validator(file) {
        if (!file) return true;
        const IMAGE_TAG_REGEX = /\.(jpg|jpeg)$/i;
        const MAX_SIZE = 2 * 1024 * 1024;
        return IMAGE_TAG_REGEX.test(file.name) && file.size <= MAX_SIZE;
    },
};
const locationIsValid = ref(true);

const rules = computed(() => ({
    title: { required, maxLength: maxLength(200), $lazy: true },
    city: { required, maxLength: maxLength(50), isValid: () => locationIsValid.value, $lazy: true },
    zipCode: { required, maxLength: maxLength(5), minLength: minLength(5), isValid: () => locationIsValid.value, $lazy: true },
    image: { fileRules, $lazy: true },
    instrumentId: { required, $lazy: true },
    styleId: { required, $lazy: true },
    goalId: { required, $lazy: true },
    description: { required, maxLength: maxLength(600), $lazy: true },
    contactMail: { required, email, maxLength: maxLength(255), $lazy: true },
}));

const v$ = useVuelidate(rules, form);

const handleImageUpload = (event) => {
    const file = event.target.files[0];
    if (file) form.image = file;
};

const handleSubmit = async () => {
    v$.value.$touch();
    if (v$.value.$invalid) {
        emit('error', t('errorValidationMessage'))
        return;
    }
    const isValidLocation = await isCityAndZipValid(form.city, form.zipCode);
    //locationIsValid.value = isValidLocation;
    if (!isValidLocation) {
        v$.value.city.$touch();
        v$.value.zipCode.$touch();
        emit('error', 'invalid-location');
        return;
    }
    const formData = new FormData();
    Object.entries(form).forEach(([key, value]) => {
        if (value !== null && value !== '') {
            const sanitized = typeof value === 'string' ? DOMPurify.sanitize(value.trim()) : value;
            formData.append(key, sanitized);
        }
    });

    try {
        const url = props.mode === 'edit' ? `/offers/${props.initialData.id}` : '/offers';
        const method = props.mode === 'edit' ? 'patch' : 'post';

        const response = await apiClient[method](url, formData, {
            headers: { 'Content-Type': 'multipart/form-data' },
        });

        if (response.status >= 200 && response.status < 300) {
            emit('success', t(props.mode === 'edit' ? 'updateSuccess' : 'successOfferMessage'))
        } else {
            throw new Error(t('serverError'));
        }
    } catch (error) {
        if (error.response?.status === 401) {
            emit('error', t('errorSession'))
            localStorage.removeItem('jwt');
            window.location.href = '/login';
        } else {
            console.error(error);
            emit('error', t('errorUnexpected'))
        }
    }
};
</script>

<template>
    <form @submit.prevent="handleSubmit">
        <div class="mt-3">
            <label for="title" class="form-label fw-medium label-required">{{ $t('offerTitle') }}</label>
            <span v-if="v$.title.$error" class="text-danger" id="error-title" role="alert">{{ $t('errorTitle') }}</span>
            <input type="text" id="title" v-model="form.title" @blur="v$.title.$touch" class="form-control"
                :aria-describedby="v$.title.$error ? 'error-title' : null"
                :aria-invalid="v$.title.$error ? 'true' : 'false'" />
        </div>

        <div class="row g-3 my-3">
            <div class="col-md-6">
                <label for="city" class="form-label fw-medium label-required">{{ $t('city') }}</label>
                <span v-if="v$.city.$error" class="text-danger" id="error-city" role="alert">{{ $t('errorCity')
                }}</span>
                <input type="text" id="city" v-model="form.city" @blur="v$.city.$touch" class="form-control"
                    aria-describedby="error-city" :aria-invalid="v$.city.$error ? 'true' : 'false'" />
            </div>
            <div class="col-md-6">
                <label for="zipCode" class="form-label fw-medium label-required">{{ $t('zipCode') }}</label>
                <span v-if="v$.zipCode.$error" class="text-danger" id="error-zipCode" role="alert">{{ $t('errorZipCode')
                }}</span>
                <input type="text" id="zipCode" v-model="form.zipCode" @blur="v$.zipCode.$touch" class="form-control"
                    aria-describedby="error-zipCode" :aria-invalid="v$.zipCode.$error ? 'true' : 'false'" />
            </div>
        </div>

        <div class="my-3">
            <label for="image" class="form-label fw-medium">{{ $t('picture') }}</label>
            <span v-if="v$.image.$error" class="text-danger" id="error-image" role="alert">{{ $t('errorPicture')
            }}</span>
            <input type="file" id="image" class="form-control" accept="image/jpeg" @change="handleImageUpload"
                aria-describedby="error-image" :aria-invalid="v$.image.$error ? 'true' : 'false'" />
        </div>

        <div class="row g-3 my-3">
            <span class="form-label fw-medium label-required">{{ $t('composeYourAd') }}</span>
            <div class="col-md-4">
                <SelectPreference id="instrumentId" :label="$t('instrument')" :options="instruments"
                    v-model="form.instrumentId" :error="v$.instrumentId.$error" :errorText="$t('errorSelect')"
                    labelClass="bg-danger text-white rounded-pill px-3 py-1" />
            </div>
            <div class="col-md-4">
                <SelectPreference id="styleId" :label="$t('style')" :options="styles" v-model="form.styleId"
                    :error="v$.styleId.$error" :errorText="$t('errorSelect')"
                    labelClass="rounded-pill px-3 py-1 text-bg-warning" />
            </div>
            <div class="col-md-4">
                <SelectPreference id="goalId goal " :label="$t('goal')" :options="goals" v-model="form.goalId"
                    :error="v$.goalId.$error" :errorText="$t('errorSelect')"
                    labelClass="rounded-pill px-3 py-1 text-bg-primary" />
            </div>
        </div>

        <div class="my-3">
            <label for="description" class="form-label fw-medium label-required">{{ $t('description') }}</label>
            <span v-if="v$.description.$error" class="text-danger" id="error-description" role="alert">{{
                $t('errorDescription')
            }}</span>
            <textarea id="description" v-model="form.description" @blur="v$.description.$touch" class="form-control"
                rows="3" aria-describedby="error-description"
                :aria-invalid="v$.description.$error ? 'true' : 'false'"></textarea>
        </div>

        <div class="mt-3">
            <label for="contactMail" class="form-label fw-medium label-required">{{ $t('contactEmail') }}</label>
            <span v-if="v$.contactMail.$error" class="text-danger" id="error-contactMail" role="alert">{{
                $t('errorEmail') }}</span>
            <input type="text" id="contactMail" v-model="form.contactMail" @blur="v$.contactMail.$touch"
                class="form-control" aria-describedby="error-contactMail"
                :aria-invalid="v$.contactMail.$error ? 'true' : 'false'" />
        </div>

        <div class="mt-4">
            <button type="submit" class="btn btn-primary px-4">
                {{ mode === 'edit' ? $t('saveChanges') : $t('save') }}
            </button>
        </div>
    </form>
</template>

<style scoped>
.label-required::after {
    content: ' * ';
    color: #f75d2e;
}
</style>