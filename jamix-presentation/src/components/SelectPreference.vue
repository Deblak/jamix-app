<script setup>
defineProps({
    id: String,
    label: String,
    options: {
        type: Array,
        required: true
    },
    modelValue: [String, Number, null],
    error: Boolean,
    errorText: String,
    labelClass: { type: String, default: '' }
});

const emit = defineEmits(['update:modelValue']);
</script>

<template>
    <div class="mb-3">
        <span v-if="error" class="text-alert" :id="`error-${id}`" role="alert">{{ errorText }}</span>
        <label :for="id" :class="['form-label', 'fw-medium', labelClass]">{{ label }}</label>
        <select :id="id" class="form-select" :aria-describedby="error ? `error-${id}` : null"
            :aria-invalid="error ? 'true' : 'false'" :value="modelValue"
            @change="emit('update:modelValue', $event.target.value)">
            <option disabled value="">{{ $t('choose') }}</option>
            <option v-for="opt in options" :key="opt.id" :value="opt.id">
                {{ opt.name || opt.type }}
            </option>
        </select>
    </div>
</template>
