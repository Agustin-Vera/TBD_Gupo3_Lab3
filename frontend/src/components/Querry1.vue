<template>
    <div class="container">
      <h1 class="title">
        Guardar un historial completo de todas las actualizaciones de precios de los productos, incluyendo la fecha y la persona que realizó la modificación.
      </h1>
  
      <table class="history-table">
        <thead>
          <tr>
            <th>Nombre del Producto</th>
            <th>Precio Anterior</th>
            <th>Precio Nuevo</th>
            <th>Fecha</th>
            <th>Persona</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="record in priceHistory" :key="record.id">
            <td>{{ record.name_product }}</td>
            <td>{{ record.original_price }}</td>
            <td>{{ record.new_price }}</td>
            <td>{{ record.update_date }}</td>
            <td>{{ record.client.name }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { getLogs } from '../services/querryService';
  
  const priceHistory = ref([]);
  
  onMounted(async () => {
    await getHistoryPrice();
  });
  
  const getHistoryPrice = async () => {
    
    const response = await getLogs();
    priceHistory.value = response.data;
  };
  </script>
  
  <style scoped>
  .container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
  }
  
  .title {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 20px;
    text-align: center;
  }
  
  .history-table {
    width: 100%;
    max-width: 800px;
    border-collapse: collapse;
  }
  
  .history-table th,
  .history-table td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: left;
  }
  
  .history-table th {
    background-color: #f2f2f2;
  }
  </style>
  