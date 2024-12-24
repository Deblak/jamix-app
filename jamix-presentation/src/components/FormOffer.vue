<script setup>
import { ref, defineProps, computed } from 'vue';
import useVuelidate from '@vuelidate/core';
import { required, maxLength } from '@vuelidate/validators';
import axios from 'axios';

const props = defineProps({
    id: { type: Number, required: true },
    title: { type: String, required: true },
    description: { type: String, required: true }
});

const userOffer = ref({
    title: props.title,
    description: props.description
});

const rules = computed(() => ({
    title: { required, maxLength: maxLength(200) },
    description: { required, maxLength: maxLength(600) },
}));

const v$ = useVuelidate(rules, userOffer);

const handleSubmit = () => {
    v$.value.$touch();
    if (!v$.value.$error) {
        send();
    } else {
        alert('Validation errors, please check your inputs!');
    }
};

const send = async () => {
    try {
        const response = await axios.put(`http://localhost:8080/offers/${props.id}`, userOffer.value);
        if (response.status === 200) {
            alert('L\'annonce a été mise à jour!');
        } else {
            throw new Error('Une erreur est survenue côté client ou serveur!');
        }
    } catch (err) {
        if (err.response) {

            alert(`Erreur du serveur : ${err.response.data.message || 'Erreur inconnue'}`);
        } else {

            alert('Une erreur imprévue est survenue.');
        }
        console.error(err);
    }
};
</script>

<template>
    <div class="modal fade" id="editModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
        aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">{{ $t('edit-offer') }}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form @submit.prevent="handleSubmit">
                        <div class="mb-3">
                            <label for="title" class="form-label">Titre</label>
                            <input type="text" id="title" v-model="userOffer.title" class="form-control"
                                placeholder="Modifier le titre" />

                            <span v-if="!v$.value.title.$pending && v$.value.title.$invalid">
                                Titre est requis et ne doit pas dépasser 200 caractères.
                            </span>
                        </div>
                        <div class="my-3">
                            <label for="description" class="form-label">Description</label>
                            <textarea id="description" v-model="userOffer.description" class="form-control" rows="3"
                                placeholder="Modifier la description"></textarea>
                            <span v-if="!v$.value.description.$pending && v$.value.description.$invalid">
                                Description est requise et ne doit pas dépasser 600 caractères.
                            </span>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                    <button type="submit" class="btn btn-primary">Sauvegarder</button>
                </div>
            </div>
        </div>
    </div>
</template>
