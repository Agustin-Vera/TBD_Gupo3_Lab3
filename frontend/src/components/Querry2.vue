<template>
    <div class="container">
      <h1>Permitir a los clientes almacenar múltiples direcciones de envío en sus perfiles.</h1>
  
      <!-- Tabla de direcciones guardadas -->
      <table class="address-table">
        <thead>
          <tr>
            <th>#</th>
            <th>Dirección</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(address, index) in addresses" :key="index">
            <td>Dirección {{ index + 1 }}</td>
            <td>{{ address }}</td>
          </tr>
        </tbody>
      </table>
  
      <!-- Formulario para agregar nueva dirección -->
      <div class="address-form">
        <h2>Agregar Nueva Dirección</h2>
        <input 
          type="text" 
          placeholder="Ingresa una dirección" 
          ref="locationInput" 
          class="input-address"
        />
        <div ref="mapContainer" class="map"></div>
        <button @click="addNewAddress">Guardar Dirección</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  
  const addresses = ref([]); // Lista de direcciones guardadas
  const locationInput = ref(null); // Input para ingresar la dirección
  const mapContainer = ref(null); // Contenedor del mapa
  const latitude = ref(null); // Coordenada latitud
  const longitude = ref(null); // Coordenada longitud
  
  const keyMaps = import.meta.env.VITE_API_GOOGLE_MAPS; // Clave API de Google Maps
  
  onMounted(() => {
    cargarMapa();
  });
  
  // Función para cargar el mapa y configurar el autocompletado
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
        fields: ['geometry', 'formatted_address'],
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
      });
    });
  };
  
  // Función para agregar una nueva dirección a la lista
  const addNewAddress = () => {
    if (locationInput.value.value.trim() === '') {
      alert('Por favor, ingresa una dirección válida.');
      return;
    }
  
    //Cambiar por llamado al backend para guardar la dirección
    addresses.value.push(locationInput.value.value); 


    locationInput.value.value = ''; // Limpiar el campo de entrada
  };
  </script>
  
  <style scoped>
  .container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
  }
  
  .address-table {
    width: 100%;
    max-width: 600px;
    border-collapse: collapse;
    margin-bottom: 20px;
  }
  
  .address-table th,
  .address-table td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: left;
  }
  
  .address-table th {
    background-color: #f2f2f2;
  }
  
  .address-form {
    width: 100%;
    max-width: 600px;
  }
  
  .input-address {
    width: calc(100% - 20px);
    padding: 10px;
    margin-bottom: 10px;
  }
  
  .map {
    width: calc(100% - 20px);
    height: 300px;
    margin-bottom: 10px;
  }
  
  button {
    padding: 10px;
    background-color: green;
    color: white;
    border: none;
  }
  </style>
  