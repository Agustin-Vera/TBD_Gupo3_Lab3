<template>
  <div class="navbar">
    <router-link to="/">
      <button class="home-button">
        <h3>Gestión de Productos</h3>
      </button>
    </router-link>
    <div class="auth-buttons">
      <div class="login-button" v-if="!loginState">
        <router-link to="/registerUser">
          <button class="register-btn">Registrar usuario</button>
        </router-link>
        <router-link to="/login">
          <button class="login-btn">Ingresar</button>
        </router-link>
      </div>
      <div class="logout-button" v-else>
        <router-link to="/logs">
          <button class="logs-btn">Bitácora</button>
        </router-link>
        <router-link to="/restrictedCommune">
          <button class="logs-btn">Agregar comuna restringida</button>
        </router-link>
        <router-link to="/querry1">
          <button class="dropdown-item">Querry 1</button>
        </router-link>
        <router-link to="/querry2">
          <button class="dropdown-item">Querry 2</button>
        </router-link>
        <router-link to="/querry3">
          <button class="dropdown-item">Querry 3</button>
        </router-link>
        <router-link to="/login">
          <button class="logout-btn" v-on:click="logout">Cerrar sesión</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useStore } from 'vuex';
import { computed } from 'vue';
import { logoutUser } from '../services/clientService';

const store = useStore();
const loginState = computed(() => store.getters.getLogin);

const logout = async () => {
  const response = await logoutUser();

  if (response.status === 200) {
    alert("Usuario desconectado");
    store.commit("logout");
    store.commit("clearUser");
  } else {
    alert("Error al desconectar");
  }
};
</script>

<style scoped>
.navbar {
  background-color: #333;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  color: white;
}

.home-button {
  background: none;
  border: none;
  color: white;
  font-size: 1.2rem;
  cursor: pointer;
}

.home-button h3 {
  margin: 0;
}

.auth-buttons {
  display: flex;
  gap: 10px;
}

button {
  background-color: #555;
  color: white;
  border: none;
  padding: 8px 12px;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #777;
}

.register-btn,
.login-btn,
.logs-btn,
.logout-btn {
  font-size: 0.9rem;
}

.logout-btn {
  background-color: #d9534f;
}

.logout-btn:hover {
  background-color: #c9302c;
}
</style>