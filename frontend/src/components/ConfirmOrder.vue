<template>
    <div class="confirm-order">
      <h1>Confirmación de Orden</h1>
      <p>Número de Orden: {{ orderID }}</p>
      <p>Nombre de Usuario: {{ user.name }}</p>
      <p>Correo de contacto: {{ user.email }}</p>
      <p>Total de la Orden: {{ total }}</p>
  
      <div class="address-selection">
        <h2>Seleccionar Dirección</h2>
        <input 
          type="text" 
          placeholder="Ingresa una dirección" 
          ref="locationInput" 
        />
        <div ref="mapContainer" class="map"></div>        
      </div>
      <button @click="actualizarOrder">Pagar orden</button>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useStore } from 'vuex';
  import { getUser } from '../services/clientService';
  import { orderService } from '../services/orderService';
  import { getAllDistributors } from '../services/distributor';
  
  
  const locationInput = ref(null);
  const mapContainer = ref(null);
  const latitude = ref(null);
  const longitude = ref(null);
  
  const keyMaps = import.meta.env.VITE_API_GOOGLE_MAPS;
  
  const store = useStore();
  const orderID = store.getters.getOrderId;
  const userId = store.getters.getUserId;
  
  const total = ref(0);
  const user = ref({});
  const distribuitor = ref({});
  
  onMounted(async () => {    
    
    cargarMapa();
    obtenerOrden();
    obtenerUsuario();
    getDistribuitor();
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
      });
    });
  }
  
  const obtenerOrden = async () => {
    try {
      const response = await orderService.gerOrderById(orderID);
      total.value = response.total;
    } catch (error) {
      console.error('Error al obtener la orden:', error);
    }
  };
  
  const obtenerUsuario = async () => {
    try {
      const response = await getUser(userId);
      user.value = response.data;
    } catch (error) {
      console.error('Error al obtener el usuario:', error);
    }
  };


  const getDistribuitor = async () => {
    const response = await getAllDistributors();
    distribuitor.value = response.data;    
  };

  const actualizarOrder = async (newOrderDetails) => {
    const orderID = store.getters.getOrderId;
    const response_order = await orderService.gerOrderById(orderID);

    response_order.latitude= latitude.value;
    response_order.longitude= longitude.value;
    response_order.state = "pagada";

    const randomDistribuitorId = distribuitor.value[Math.floor(Math.random() * distribuitor.value.length)].id;
    response_order.distributor_id = randomDistribuitorId;

    const response = await orderService.putOrder(response_order);
    console.log(response);
};

  </script>
  
  <style scoped>
  .confirm-order {
    text-align: center;
    margin-top: 20px;
  }
  
  .confirm-order h1 {
    font-size: 24px;
    margin-bottom: 20px;
  }
  
  .confirm-order p {
    font-size: 18px;
    margin: 5px 0;
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

  button {
    background-color: #4CAF50; /* Verde elegante */
    color: white;
    font-size: 16px;
    font-weight: bold;
    border: none;
    border-radius: 8px; /* Bordes redondeados */
    padding: 12px 20px;
    cursor: pointer;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2); /* Sombra suave */
    transition: all 0.3s ease; /* Suaviza la animación */
  }

  button:hover {
    background-color: #45a049; /* Color más oscuro al pasar el mouse */
    box-shadow: 0 6px 8px rgba(0, 0, 0, 0.3); /* Aumenta la sombra */
    transform: translateY(-2px); /* Efecto de elevación */
  }

  button:active {
    transform: translateY(2px); /* Efecto de presionado */
    box-shadow: 0 3px 5px rgba(0, 0, 0, 0.2);
  }
  </style>
  