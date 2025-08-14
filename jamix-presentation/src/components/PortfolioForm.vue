<script setup>
import { computed, reactive, watch } from 'vue';
import useVuelidate from '@vuelidate/core';
import { required, maxLength } from '@vuelidate/validators';
import { useI18n } from 'vue-i18n';
import DOMPurify from 'dompurify';
import apiClient from '@/services/axiosApi.js';

const props = defineProps({
    mode: { type: String, default: 'create' },
    initialData: { type: Object, default: () => ({}) },
});

const emit = defineEmits(['success', 'error'])

const { t } = useI18n();

const form = reactive({
    bandName: '',
    tagline: '',
    biography: '',
    imageUrl: ''
});

watch(
    () => props.initialData,
    (newData) => {
        if (props.mode === 'edit' && newData) {
            Object.assign(form, newData);
        }
    },
    { immediate: true }
);

const fileRules = {
    $validator(file) {
        if (!file) return true;
        const IMAGE_TAG_REGEX = /\.(jpg|jpeg)$/i;
        const MAX_SIZE = 5 * 1024 * 1024;
        return IMAGE_TAG_REGEX.test(file.name) && file.size <= MAX_SIZE;
    },
};

const rules = computed(() => ({
    bandName: { required, maxLength: maxLength(50) },
    tagline: { maxLength: maxLength(120) },
    biography: { maxLength: maxLength(800) },
    image: { fileRules }
}));

const v$ = useVuelidate(rules, form, { $lazy: true });

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
    const formData = new FormData();
    Object.entries(form).forEach(([key, value]) => {
        if (value !== null && value !== '') {
            const sanitized = typeof value === 'string' ? DOMPurify.sanitize(value.trim()) : value;
            formData.append(key, sanitized);
        }
    });

    try {
        const url = '/portfolios/owned';
        const method = props.mode === 'edit' ? 'patch' : 'post';

        const response = await apiClient[method](url, formData);

        if (response.status >= 200 && response.status < 300) {
            emit('success', t(props.mode === 'edit' ? 'updatePortfolioSuccess' : 'createPortfolioSuccess'))
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
            <label for="bandName" class="form-label fw-medium label-required">{{ $t('bandName') }}</label>
            <span v-if="v$.bandName.$error" class="text-alert" id="error-bandName" role="alert">{{ $t('errorBandName')
            }}</span>
            <input type="text" id="bandName" v-model="form.bandName" @blur="v$.bandName.$touch"
                class="form-control radius-square" :aria-describedby="v$.bandName.$error ? 'error-bandName' : null"
                :aria-invalid="v$.bandName.$error ? 'true' : 'false'" />
        </div>

        <div class="mt-3">
            <label for="tagline" class="form-label fw-medium">{{ $t('tagline') }}</label>
            <span v-if="v$.tagline.$error" class="text-alert" id="error-tagline" role="alert">{{ $t('errorTagline')
            }}</span>
            <input type="text" id="tagline" v-model="form.tagline" @blur="v$.tagline.$touch"
                class="form-control radius-square" aria-describedby="error-tagline"
                :aria-invalid="v$.tagline.$error ? 'true' : 'false'" />
        </div>

        <div class="my-3">
            <label for="biography" class="form-label fw-medium">{{ $t('biography') }}</label>
            <span v-if="v$.biography.$error" class="text-alert" id="error-biography" role="alert">{{
                $t('errorBiography')
            }}</span>
            <textarea id="biography" v-model="form.biography" @blur="v$.biography.$touch"
                class="form-control radius-square" rows="6" aria-describedby="error-biography"
                :aria-invalid="v$.biography.$error ? 'true' : 'false'"></textarea>
        </div>

        <div class="my-3">
            <label for="image" class="form-label fw-medium">{{ $t('picture') }}</label>
            <span v-if="v$.image.$error" class="text-alert" id="error-image" role="alert">{{ $t('errorPicture')
            }}</span>
            <input type="file" id="image" class="form-control radius-square" accept="image/jpeg"
                @change="handleImageUpload" aria-describedby="error-image"
                :aria-invalid="v$.image.$error ? 'true' : 'false'" />
        </div>

        <div class="mt-4">
            <button type="submit" class="btn btn-primary px-4">
                {{ mode === 'edit' ? $t('saveChanges') : $t('save') }}
            </button>
        </div>
    </form>
</template>