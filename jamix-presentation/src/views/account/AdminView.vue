<script setup>
import { ref, onMounted } from 'vue';
import apiClient from '@/services/axiosApi.js';
import { useI18n } from 'vue-i18n';
import { useSwalFire } from '@/composables/useSwalFire';
import { useAppStore } from '@/stores/useAppStore';
const { swalConfirm, swalError } = useSwalFire();
const store = useAppStore();
const { t } = useI18n();

const accounts = ref([]);
const loading = ref(true);
const error = ref(null);

async function handleDelete(id) {
    const result = await swalConfirm(t('confirmOfferDelete'));

    if (!result.isConfirmed) return;

    try {
        await apiClient.delete(`/admin/${id}`);
        accounts.value = accounts.value.filter(account => account.id !== id);
        store.showToast(t('deleteAccountSuccess'));
    } catch (error) {
        console.error(error);
        swalError(t('errorSwalTitle'), t('errorUnexpectedMessage'));
    }
}

onMounted(async () => {
    try {
        const response = await apiClient.get('/admin');
        accounts.value = response.data;
    } catch (err) {
        error.value = 'Failed to fetch accounts.';
        console.error(err);
    } finally {
        loading.value = false;
    }
});
</script>

<template>
    <main class="container py-4">
        <h1 class="mb-4">{{ t('userList') }}</h1>

        <div v-if="loading">{{ t('loading') }}</div>

        <div v-else-if="error" class="alert alert-danger" role="alert">
            {{ error }}
        </div>

        <div v-else-if="accounts.length === 0">
            <p>{{ t('noAccountsFound') }}</p>
        </div>

        <table v-else class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th scope="col">{{ t('name') }}</th>
                    <th scope="col">{{ t('email') }}</th>
                    <th scope="col">{{ t('action') }}</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="account in accounts" :key="account.email">
                    <td>{{ account.username }}</td>
                    <td>{{ account.email }}</td>
                    <td><button class="btn btn-sm btn-jm-danger" @click="handleDelete(account.id)">{{ t('delete')
                    }}</button></td>
                </tr>
            </tbody>
        </table>
    </main>
</template>
