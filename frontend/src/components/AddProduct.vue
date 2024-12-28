<template>
  <div>
    <h2 class="form-title">Rellene el siguiente formulario para crear un producto:</h2>

    <form @submit.prevent="registerProduct" class="product-form">
      <div class="form-group">
        <label for="name">Nombre:</label>
        <input type="text" id="name" name="name" v-model="product.name" class="input-field" />
      </div>

      <div class="form-group">
        <label for="description">Descripción:</label>
        <input type="text" id="description" name="description" v-model="product.description" class="input-field" />
      </div>

      <div class="form-group">
        <label for="price">Precio:</label>
        <input type="number" id="price" name="price" v-model.number="product.price" class="input-field" />
      </div>

      <div class="form-group">
        <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" v-model.number="product.stock" class="input-field" />
      </div>



      <div class="form-group">
        <label for="category">Categoría:</label>
        <select id="category" v-model="product.categoryId" class="input-field">
          <option disabled value="">Seleccione una categoría</option>
          <option v-for="category in categories" :key="category.id" :value="category.id">
            {{ category.name }}
          </option>
        </select>
      </div>

      <button type="submit">Crear</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import productService from '../services/productService';
import categoryService from '../services/categoryService';

const product = ref({
  name: '',
  description: '',
  price: 0,
  stock: 0,
  state: 'available',
  categoryId: null,
});

const categories = ref([]);

const registerProduct = async () => {
  try {
    const response = await productService.postProduct(product.value, product.value.categoryId);

    product.value = {
      name: '',
      description: '',
      price: 0,
      stock: 0,
      state: 'available',
      categoryId: null,
    };
  } catch (error) {
    console.error(error.message);
  }
};

onMounted(async () => {
  try {
    const responseCategories = await categoryService.getCategories();
    categories.value = responseCategories;
  } catch (error) {
    console.error(error.message);
  }
});
</script>

<style scoped>
.product-form {
  display: flex;
  flex-direction: column;
  max-width: 400px;
  margin: auto;
}

.form-title {
  margin-top: 15px;
  text-align: center;
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 15px;
}

.input-field {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.input-field:focus {
  border-color: #007bff;
}

button {
  padding: 8px 16px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
}

button:hover {
  background-color: #0056b3;
}
</style>