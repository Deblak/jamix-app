<script setup>
import { watch, ref } from 'vue'
import { useAppStore } from '@/stores/useAppStore'
const store = useAppStore()

const toastTime = ref('')

watch(() => store.toastMessage, (newVal) => {
    if (newVal) {
        toastTime.value = new Date().toLocaleTimeString()
        setTimeout(() => {
            store.clearToast()
        }, 5000)
    }
})
</script>

<template>
    <div class="toast-container position-fixed bottom-0 end-0 p-3">
        <div v-if="store.toastMessage" class="toast show" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <i class="bi bi-check-circle-fill text-primary me-2" aria-hidden="true"></i>
                <strong class="me-auto">Jamix</strong>
                <small>{{ toastTime }}</small>
                <button type="button" class="btn-close" @click="store.clearToast" aria-label="Fermer"></button>
            </div>
            <div class="toast-body">
                {{ store.toastMessage }}
            </div>
        </div>

        <!-- Accessibility screen-reader -->
        <output v-if="store.screenReaderMessage" class="visually-hidden" aria-live="assertive">
            {{ store.screenReaderMessage }}
        </output>
    </div>
</template>
