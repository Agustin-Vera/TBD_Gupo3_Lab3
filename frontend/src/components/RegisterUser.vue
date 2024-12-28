<template>
    <div class="register-container">
        <h1>Registro de Nuevo Usuario</h1>
        <form @submit.prevent="registerUser">
            <div>
                <label for="name">Nombre:</label>
                <input type="text" id="name" v-model="userData.name" required />
            </div>            
            <div>
                <label for="email">Correo:</label>
                <input type="email" id="email" v-model="userData.email" required />
            </div>
            <div>
                <label for="password">Contraseña:</label>
                <input type="password" id="password" v-model="userData.password" required />
            </div>
            <div>
                <label for="phone">Teléfono:</label>
                <input type="tel" id="phone" v-model="userData.phone" required />
            </div>

            <div class="address-selection">
        <h2>Seleccionar Dirección</h2>
        <input 
          type="text" 
          placeholder="Ingresa una dirección" 
          ref="locationInput" 
        />
        <div ref="mapContainer" class="map"></div>
        <p>Latitud: {{ latitude }}</p>
        <p>Longitud: {{ longitude }}</p>
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



const cargarMapa = async () =>{
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
.register-container {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
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

.address-selection {
    max-width: 800px;
    margin: 0 auto;
  }
  
  .address-selection h2 {
    text-align: center;
  }
  
  .address-selection input {
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