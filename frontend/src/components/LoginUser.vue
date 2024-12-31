<template>
    <div class="container">
        <section class="login-container">
            <h1>Iniciar Sesión</h1>
            <form @submit.prevent="login">
                <div>
                    <label for="email">Correo:</label>
                    <input type="email" v-model="userData.email" required />
                </div>
                <div>
                    <label for="password">Contreseña:</label>
                    <input type="password" v-model="userData.password" required />
                </div>
                <button class="login-button" type="submit">Iniciar Sesión</button>
            </form>
        </section>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { loginUser } from '../services/clientService';
import { useStore } from 'vuex';
import { orderService } from '../services/orderService';
import { useRouter } from 'vue-router';

const userData = ref({ email: '', password: '' });
const store = useStore();

const router = useRouter();

const login = async () => {
    const response = await loginUser(userData.value);
    if (response.status === 200) {
        store.commit('setUser', response.data);
        store.commit('login');
        store.commit('setUserId', response.data.user_id);
        alert('Sesión iniciada correctamente');
        newOrder();
        router.push('/');
    } else {
        alert('Error al iniciar sesión');
    }


};

const newOrder = async () => {

    const DataNewOrder = {
        "order_date": new Date().toISOString(),
        "state": "Pendiente",
        "client_id": store.getters.getUserId,
        "total": 0
    }

    const response = await orderService.postOrder(DataNewOrder);
    store.commit('setOrderId', response.id);
    return response;
}

</script>

<style scoped>
.container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 50vh;
}

.login-container {
    display: grid;
    max-width: 400px;
    align-items: center;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 100%;
}

h1 {
    text-align: center;
}

form div {
    margin-bottom: 15px;
}

label {
    display: block;
    margin-bottom: 5px;
}

input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
}

button {
    width: 100%;
    padding: 10px;
    background-color: #007BFF;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
    scale: 101%;
}
</style>
