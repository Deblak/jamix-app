<script setup>
import { useSwalFire } from '@/composables/useSwalFire.js';
const { swalError } = useSwalFire();

function showError() {
    swalError('Test d’erreur', 'Ceci est un message de test.');
}
</script>

<template>
    <div class="container py-5">
        <h2>Test SweetAlert2</h2>
        <button class="btn btn-danger" @click="showError">
            Afficher une alerte d’erreur
        </button>
    </div>
</template>
