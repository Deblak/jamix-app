<script setup>
import { computed, ref } from 'vue';
import useVuelidate from '@vuelidate/core';
import { required, maxLength } from '@vuelidate/validators';
import axios from 'axios';

const createForm = ref({
    title: '',
    description: ''
});

const rules = computed(() => {
    return {
        title: {
            required,
            maxLength: maxLength(200)
        },
        description: {
            required,
            maxLength: maxLength(600)
        }
    }
})
const v$ = useVuelidate(rules, createForm);

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
        const response = await axios.post('http://localhost:8080/offers/create', createForm.value);
        if (response.status === 200) {
            createForm.value = {};
            alert('L\'annonce est sous le feu des projecteurs!');
        } else {
            throw new Error('A client or server error has occurred!');
        }
    } catch (err) {
        alert('An unexpected error has occurred!');
        console.error(err);
    }
};


</script>
<template>
    <section class="d-lg-flex flex-wrap justify-content-center">
        <h1 class="header col-12">Nouvelle annonce, nouveau rythme</h1>
        <div class="p-4 col-lg-6 jm-card-border bg-light">
            <small><span class="text-danger">*</span> Champs obligatoires</small>
            <form id="create-form" @submit.prevent="handleSubmit">
                <div class="mt-4 mb-2">
                    <label class="form-label fw-medium" for="title">Titre de l'annonce<span
                            class="text-danger">*</span></label>
                    <input type="text" id="title" v-model="createForm.title" class="form-control"
                        placeholder="Cherche batteur pour groupe rock">
                </div>

                <div class="row g-3 my-3">
                    <div class="col-md-6">
                        <label class="form-label fw-medium">Ville</label>
                        <input type="text" class="form-control" placeholder="Indiquez une ville ou une région">
                    </div>
                    <div class="col-md-6">
                        <label class="form-label fw-medium">Code postal</label>
                        <input type="text" class="form-control" placeholder="Indiquez le code postal">
                    </div>
                </div>

                <div class="my-3">
                    <label for="formFile" class="form-label fw-medium">Image</label>
                    <input class="form-control" type="file" id="formFile">
                </div>
                <div class="row g-3 my-3">
                    <span class="fw-medium">Composez votre annonce :</span>
                    <div class="col-md-4 mt-2">
                        <label for="formFile"
                            class="form-label badge rounded-pill text-bg-primary fw-medium txt-small">Instrument</label>
                        <select class="form-select form-select mb-3" aria-label="Choose instrument">
                            <option selected>Choisissez</option>
                            <option value="1">Basse</option>
                            <option value="2">Batterie</option>
                            <option value="3">Chant</option>
                            <option value="4">Guitare</option>
                        </select>
                    </div>
                    <div class="col-md-4  mt-2">
                        <label for="formFile"
                            class="form-label badge rounded-pill text-bg-warning fw-medium txt-small">Style</label>
                        <select class="form-select form-select mb-3" aria-label="Choose music style">
                            <option selected>Choisissez</option>
                            <option value="1">Multiple</option>
                            <option value="2">Blues</option>
                            <option value="3">Jazz</option>
                            <option value="4">Pop Rock</option>
                        </select>
                    </div>
                    <div class="col-md-4  mt-2">
                        <label for="formFile"
                            class="form-label badge rounded-pill text-bg-danger fw-medium txt-small">Objectif</label>
                        <select class="form-select form-select mb-3" aria-label="Choose goal">
                            <option selected>Choisissez</option>
                            <option value="1">Entraînement</option>
                            <option value="2">Jam Session</option>
                            <option value="3">Monter un groupe</option>
                        </select>
                    </div>
                </div>

                <div class="my-3">
                    <label for="description" class="form-label fw-medium">Description<span
                            class="text-danger">*</span></label>
                    <textarea type="text" v-model="createForm.description" id="description"
                        class="form-control px-3 py-2" rows="3"></textarea>
                </div>

                <div class=" mt-2">
                    <label class="form-label fw-medium">Mail de contact<span class="text-danger">*</span></label>
                    <div class="input-group">
                        <span class="input-group-text" id="basic-addon1"><i class="bi bi-envelope-at"></i></span>
                        <input type="text" class="form-control" placeholder="contacter-moi@ici.com" aria-label="Mail"
                            aria-describedby="basic-addon1">
                    </div>
                </div>

                <div class="mt-5">
                    <a class="btn px-4 btn-outline-secondary me-md-4 jm-shadow-box">Prévisualiser</a>

                    <button type="submit" class="btn px-4 btn-primary jm-shadow-box">Enregistrer</button>
                </div>
            </form>
        </div>
    </section>
</template>