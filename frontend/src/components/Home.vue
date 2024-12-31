<template>
  <div>
    <h1 class="tittle">Productos</h1>

    <div class="button-container">
      <router-link to="/addProduct">
        <button style="background-color: green; color: white;">Agregar Producto</button>
      </router-link>

      <router-link to="/order">
        <button style="background-color: orange; color: white;">Ver Orden</button>
      </router-link>
    </div>

    <table class="product-table">
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Precio</th>
          <th>Stock</th>
          <th>Unidades a Pedir</th>
          <th>Lista de deseos</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="product in products" :key="product.id">
          <td>{{ product.name }}</td>
          <td>{{ product.price }}</td>
          <td>{{ product.stock }}</td>
          <td class="unit-container">
            <input class="unit-input" type="number" v-model.number="product.quantity" min="0" :max="product.stock" />
            <button class="unit-button" @click="sendProductId(product, product.quantity)"
              :disabled="!isValidQuantity(product.quantity, product.stock)">
              Agregar a orden de compra
            </button>
          </td>
          <td>
            <button @click="agregarListaDeseos(product)" style="background-color: green; color: white;">
              Agregar a lista de deseos
            </button>
          </td>

        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import productService from '../services/productService';
import { orderService } from '../services/orderService';
import { useStore } from 'vuex';
import { AddProductToWishList } from '../services/wishListService';
import { getUser } from '../services/clientService';


const products = ref([]);
const store = useStore();
const userId = store.getters.getUserId;


onMounted(async () => {
  try {
    const responseProducts = await productService.getProducts();
    products.value = responseProducts.map((product) => ({
      ...product,
      quantity: 0,
    }));
  } catch (error) {
    console.error(error.message);
  }

});

const isValidQuantity = (quantity, stock) => {
  return quantity > 0 && quantity <= stock;
};

const sendProductId = async (product, cantidad) => {
  cantidad = parseInt(cantidad);

  const newOrderDetails = {
    order_id: store.getters.getOrderId,
    product_id: product.id,
    quantity: cantidad,
    unit_price: product.price,
  };

  if (cantidad > product.stock) {
    alert('No hay suficiente stock');
    return;
  }

  try {
    const response = await orderService.postOrderDetails(newOrderDetails);
    //console.log( response);
    await actualizarTotal(newOrderDetails);
    await actualizarStock(product, cantidad);
  } catch (error) {
    console.error(error.message);
  }
};

const actualizarTotal = async (newOrderDetails) => {
  const orderID = store.getters.getOrderId;
  const response_order = await orderService.gerOrderById(orderID);

  response_order.total =
    response_order.total + newOrderDetails.quantity * newOrderDetails.unit_price;

  const response = await orderService.putOrder(response_order);
};

const actualizarStock = async (product, cantidad) => {
  product.stock = product.stock - cantidad;
  console.log(product);


  const response = await productService.putProduct(product.id, product);
};




const agregarListaDeseos = async (product) => {
  const user = await getUser(userId);

  const productoNew = {
    products: [product.id]
  }

  const response = await AddProductToWishList(user.data.wishlist.id, productoNew);
};

</script>

<style scoped>
.unit-input {
  width: 50px;
  padding: 5px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.unit-button {
  background-color: burlywood;
  color: white;
  padding: 5px;
  border-radius: 5px;
  cursor: pointer;
}

.unit-container {
  display: flex;
  gap: 10px;
}

.tittle {
  text-align: center;
}

.button-container {
  display: flex;
  margin-bottom: 20px;
  justify-content: center;
}

.form-title {
  margin-top: 20px;
}

.product-table {
  max-width: 50%;
  width: 100%;
  border-collapse: collapse;
  margin: 20px auto;
}

.product-table th,
.product-table td {
  border: 1px solid #ccc;
  padding: 10px;
  text-align: left;
}

.product-table th {
  background-color: #f2f2f2;
}

button {
  padding: 5px;
  border-radius: 5px;
  cursor: pointer;
  margin: 0 5px;
}
</style>