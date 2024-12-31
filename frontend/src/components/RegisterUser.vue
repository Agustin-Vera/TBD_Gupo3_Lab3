<template>
  <div class="register-container">
    <h1 class="tittle">Registro de Nuevo Usuario</h1>
    <form class="register-form" @submit.prevent="registerUser">
      <div class="form-container">
        <section class="user-data">
          <!-- Name -->
          <label for="name">
            Nombre:
            <input type="text" id="name" v-model="userData.name" required />
          </label>
          <!-- Email -->
          <label for="email">
            Correo:
            <input type="email" id="email" v-model="userData.email" required />
          </label>
          <!-- Password -->
          <label for="password">
            Contraseña:
            <input type="password" id="password" v-model="userData.password" required />
          </label>
          <!-- Phone -->
          <label for="phone">
            Teléfono:
            <input type="tel" id="phone" v-model="userData.phone" required />
          </label>
        </section>
        <section class="address-data">
          <h2>Seleccionar Dirección</h2>
          <input type="text" placeholder="Ingresa una dirección" ref="locationInput" />
          <div ref="mapContainer" class="map"></div>
          <p>Latitud: {{ latitude }}</p>
          <p>Longitud: {{ longitude }}</p>
        </section>
      </div>
      <button type="submit">Registrar</button>
    </form>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue';
import { postClient } from '../services/clientService';

const locationInput = ref(null);
const mapContainer = ref(null);
const latitude = ref(null);
const longitude = ref(null);

const keyMaps = import.meta.env.VITE_API_GOOGLE_MAPS;

const userData = reactive({
  name: '',
  email: '',
  password: '',
  phone: '',
  latitude: '',
  longitude: '',
});

const registerUser = async () => {
  const response = await postClient(userData);
  if (response.status === 201) {
    alert('Usuario registrado correctamente');
    window.location.href = '/login';
  } else {
    alert('Error al registrar el usuario');
  }
};

onMounted(() => {
  cargarMapa();
});



const cargarMapa = async () => {
  const { Loader } = await import('@googlemaps/js-api-loader');
  const loader = new Loader({
    apiKey: keyMaps,
    libraries: ['places'],
  });

  loader.load().then(() => {
    const google = window.google;

    const map = new google.maps.Map(mapContainer.value, {
      center: { lat: -33.437787, lng: -70.650188 },
      zoom: 11,
    });

    const autocomplete = new google.maps.places.Autocomplete(locationInput.value, {
      fields: ['geometry'],
      types: ['address'],
    });

    const marker = new google.maps.Marker({ map });

    autocomplete.addListener('place_changed', () => {
      const place = autocomplete.getPlace();
      if (!place.geometry) {
        alert('No se encontraron detalles para la dirección ingresada.');
        return;
      }

      const location = place.geometry.location;
      map.setCenter(location);
      map.setZoom(15);
      marker.setPosition(location);

      latitude.value = location.lat();
      longitude.value = location.lng();
      userData.latitude = location.lat();
      userData.longitude = location.lng();
    });
  });
}

</script>

<style scoped>
.user-data {
  display: grid;
  gap: 20px;
  margin-top: auto;
  margin-bottom: auto;
  align-items: center;
  width: 100%;
  max-width: 400px;
}

.form-container {
  display: flex;
  gap: 20px;
}

.register-form {
  display: grid;
  width: 100%;
  gap: 20px;
}

.register-container {
  max-width: 800px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  color: green;
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
  background-color: green;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: darkgreen;
}

.address-data {
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
}

.address-data h2 {
  text-align: center;
}

.address-data input {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  font-size: 16px;
}

.map {
  width: 100%;
  max-width: 600px;
  height: 300px;
  border: 1px solid #ddd;
  margin: 0 auto;
}
</style>