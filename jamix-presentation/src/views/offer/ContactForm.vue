<script setup>
import apiClient from '@/services/axiosApi.js';
import { ref, computed, onMounted } from "vue";
import { useI18n } from "vue-i18n";
import useVuelidate from "@vuelidate/core";
import { required, email, minLength, maxLength } from "@vuelidate/validators";
import { swalSwitchError } from "@/services/swalFireService.js";
import { useAppStore } from '@/stores/useAppStore';

const { t } = useI18n();
const store = useAppStore();

const props = defineProps({
    offerId: { type: String, required: true }
});

// Anti-spam
const honeypot = ref("");
const formOpenedAt = ref(0);
onMounted(() => { formOpenedAt.value = Date.now(); });

const form = ref({
    visitorName: '',
    visitorMail: '',
    visitorMessage: '',
    offerId: Number(props.offerId)
});

const rules = computed(() => ({
    visitorName: { required, maxLength: maxLength(20) },
    visitorMail: { required, email, minLength: minLength(6), maxLength: maxLength(320) },
    visitorMessage: { required, minLength: minLength(10), maxLength: maxLength(500) }
}));
const v$ = useVuelidate(rules, form);

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
        const { visitorName, visitorMail, visitorMessage, offerId } = form.value;
        const response = await apiClient.post(`/offers/contact/${offerId}`, {
            visitorName,
            visitorMail,
            visitorMessage
        });
        if (response.status === 202) {
            resetForm();
            store.showToast(t('successContactSent'), t('successContactSentSR'));
        } else {
            throw { response };
        }
    } catch (err) {
        await swalSwitchError(err);
    }
};

const resetForm = () => {
    const currentOfferId = form.value.offerId;
    form.value = { visitorName: '', visitorMail: '', visitorMessage: '', offerId: currentOfferId };
    v$.value.$reset();
};
</script>



<template>
    <main class="container-custom">
        <h1 class="display-5">{{ t('contactTitle') }}</h1>
        <div class="bg-light p-2 rounded bg-opacity-75 d-flex">
            <p class="ps-2 mb-0"><i class="bi bi-exclamation-triangle px-2 fs-3"></i>
                {{ $t('contactDesciption') }}
            </p>
        </div>
        <div class="row row-cols-lg-2 g-3">
            <div class="order-1 order-lg-0 text-center">
                <img src="/default-music.jpg" class="col-8 m-4 img-fluid rounded-3 shadow-sm" alt="" />
            </div>

            <form @submit.prevent="handleSubmit" novalidate>

                <!-- Honeypot -->
                <div class="d-none" aria-hidden="true">
                    <label for="company">Company</label>
                    <input id="company" v-model="honeypot" type="text" tabindex="-1" autocomplete="off" />
                </div>

                <p class="my-4 small">{{ t('requireLegend') }}</p>

                <!-- Name -->
                <div class="mb-3">
                    <label for="visitorName" class="form-label fw-medium label-required">{{ t('name') }}</label>
                    <span v-if="v$.visitorName.$error" class="text-alert small" role="alert" aria-live="assertive"
                        id="visitorName-error">
                        <template v-if="v$.visitorName.required.$invalid">{{ t('errorNameRequired') }}</template>
                        <template v-else-if="v$.visitorName.maxLength.$invalid">{{ t('errorNameMaxLength') }}</template>
                    </span>
                    <input id="visitorName" v-model.trim="form.visitorName" type="text" class="form-control" required
                        maxlength="20" aria-required="true" :aria-invalid="v$.visitorName.$error ? 'true' : 'false'"
                        :aria-describedby="v$.visitorName.$error ? 'visitorName-error' : null"
                        @blur="v$.visitorName.$touch();" />
                </div>

                <!-- Email -->
                <div class="mb-3">
                    <label for="visitorMail" class="form-label fw-medium label-required">{{ t('email') }}</label>
                    <span v-if="v$.visitorMail.$error" class="text-alert small" role="alert" aria-live="assertive"
                        id="visitorMail-error">
                        <template v-if="v$.visitorMail.required.$invalid">{{ t('errorEmailRequired') }}</template>
                        <template v-else-if="v$.visitorMail.email.$invalid">{{ t('errorEmailInvalid') }}</template>
                        <template v-else-if="v$.visitorMail.minLength.$invalid">{{ t('errorEmailMinLength')
                            }}</template>
                        <template v-else-if="v$.visitorMail.maxLength.$invalid">{{ t('errorEmailMaxLength')
                            }}</template>
                    </span>
                    <input id="visitorMail" v-model.trim="form.visitorMail" type="email" class="form-control" required
                        autocomplete="email" inputmode="email" maxlength="320" aria-required="true"
                        :aria-invalid="v$.visitorMail.$error ? 'true' : 'false'"
                        :aria-describedby="v$.visitorMail.$error ? 'visitorMail-error' : null"
                        @blur="v$.visitorMail.$touch();" />
                </div>

                <!-- Message -->
                <div class="mb-3">
                    <label for="visitorMessage" class="form-label fw-medium label-required">{{ t('contactMessage')
                        }}</label>
                    <span v-if="v$.visitorMessage.$error" class="text-alert small" role="alert" aria-live="assertive"
                        id="visitorMessage-error">
                        <template v-if="v$.visitorMessage.required.$invalid">{{ t('errorMessageRequired') }}</template>
                        <template v-else-if="v$.visitorMessage.minLength.$invalid">{{ t('errorMessageMinLength')
                            }}</template>
                        <template v-else-if="v$.visitorMessage.maxLength.$invalid">{{ t('errorMessageMaxLength')
                            }}</template>
                    </span>
                    <textarea id="visitorMessage" v-model.trim="form.visitorMessage" class="form-control" rows="6"
                        required maxlength="500" aria-required="true"
                        :aria-invalid="v$.visitorMessage.$error ? 'true' : 'false'"
                        :aria-describedby="v$.visitorMessage.$error ? 'visitorMessage-error' : null"
                        @blur="v$.visitorMessage.$touch();"></textarea>
                </div>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-jm-warning">{{ t('sendMail') }}</button>
                </div>
            </form>
        </div>
    </main>
</template>