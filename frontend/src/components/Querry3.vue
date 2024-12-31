<template>
    <div class="container">
        <h1 class="tittle">Permitir a los clientes crear listas de deseos personalizadas con los productos que les
            interesan comprar en
            el futuro.</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Estado</th>
                    <th>Precio</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="producto in productos" :key="producto.id">
                    <td>{{ producto.name }}</td>
                    <td>{{ producto.state }}</td>
                    <td>{{ producto.price }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>

import { ref, onMounted } from 'vue';
import { useStore } from 'vuex';
import { getUser } from '../services/clientService';

onMounted(() => {
    get_User();
});


const store = useStore();
const userId = store.getters.getUserId;

const productos = ref([]);


const get_User = async () => {
    const { data, status } = await getUser(userId);
    productos.value = data.wishlist.products;
};

</script>

<style scoped>
.tittle {
    text-align: center;
    margin-top: 20px;
}

table {
    width: 100%;
    max-width: 800px;
    border-collapse: collapse;
    margin: 40px auto;
}

th,
td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

th {
    background-color: #f2f2f2;
}
</style>