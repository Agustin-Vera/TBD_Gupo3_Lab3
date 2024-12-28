<template>
  <div>
    <h1>Orden de Compra Actual</h1>
    <table class="table">
      <thead>
        <tr>
          <th>Nombre del Producto</th>
          <th>Cantidad</th>
          <th>Precio unitario</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(product, index) in products" :key="index">
          <td>{{ product.name }}</td>
          <td>{{ product.quantity }}</td>
          <td>{{ product.unit_price.toFixed(2) }} pesos</td>
        </tr>
      </tbody>
    </table>

    <div style="text-align: center; margin-top: 20px;">
      <h2>Total: {{ total }} pesos</h2>
      <button 
        @click="confirmarOrder"
        class="confirm-button">
        Confirmar Orden
      </button>
    </div>

    <div v-if="loading" class="loader">Cargando...</div>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import productService from '../services/productService';
import { orderService } from '../services/orderService';

const products = ref([]);
const total = ref(0);
const loading = ref(true);
const errorMessage = ref('');
const store = useStore();
const router = useRouter();

const obtenerOrder = async () => {
  try {
    const orderID = store.getters.getOrderId;

    const responseOrder = await orderService.gerOrderById(orderID);
    total.value = responseOrder.total;

    const responseProducts = await orderService.getProductOrdersById(orderID);
    const productIds = responseProducts.map(order => order.product_id);
    const productRequests = productIds.map(id => productService.getProduct(id));
    const productsResponses = await Promise.all(productRequests);

    products.value = productsResponses.map((res, index) => ({
      name: res.name || 'Nombre no disponible',
      quantity: responseProducts[index].quantity,
      unit_price: responseProducts[index].unit_price,
    }));
  } catch (error) {
    errorMessage.value = 'Error al obtener productos: ' + error.message;
  } finally {
    loading.value = false;
  }
};

const confirmarOrder = () => {
  router.push('confirmOrder');
};

onMounted(obtenerOrder);
</script>

<style scoped>
.loader {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 20px;
}

.error-message {
  color: red;
  text-align: center;
  margin-top: 10px;
}

.table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

.table th,
.table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
}

.table th {
  background-color: #f2f2f2;
  font-weight: bold;
}

.confirm-button {
  background-color: green;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

.confirm-button:hover {
  background-color: darkgreen;
}
</style>
