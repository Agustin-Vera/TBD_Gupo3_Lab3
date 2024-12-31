<template>
  <div>
    <h2 class="form-title">Rellene el siguiente formulario para crear un producto:</h2>

    <form @submit.prevent="registerProduct" class="product-form">
      <div class="form-container">
        <!-- Name -->
        <label for="name">
          Nombre:
          <input type="text" id="name" name="name" v-model="product.name" class="input-field" />
        </label>
        <!-- Descriptio  -->
        <label for="description">
          Descripción:
          <input type="text" id="description" name="description" v-model="product.description" class="input-field" />
        </label>
        <!-- Price -->
        <label for="price">
          Precio:
          <input type="number" id="price" name="price" v-model.number="product.price" class="input-field" />
        </label>
        <!-- Stock -->
        <label for="stock">
          Stock:
          <input type="number" id="stock" name="stock" v-model.number="product.stock" class="input-field" />
        </label>
        <!-- Category -->
        <div class="category-container">
          <label for="category">
            Categoría:
            <select id="category" v-model="product.category_id" class="input-field">
              <option disabled value="">Seleccione una categoría</option>
              <option v-for="category in categories" :key="category.id" :value="category.id">
                {{ category.name }}
              </option>
            </select>
          </label>
        </div>
      </div>
      <button class="submit-button" type="submit">Crear</button>
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
  category_id: null,
});

const categories = ref([]);

const registerProduct = async () => {
  try {
    const response = await productService.postProduct(product.value);

    product.value = {
      name: '',
      description: '',
      price: 0,
      stock: 0,
      state: 'available',
      category_id: null,
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
.submit-button {
  margin-top: 10px;
  width: 50%;
  margin: auto;
}

.form-container {
  display: grid;
  flex-direction: column;
  grid-template-columns: 1fr 1fr;
  align-items: center;
  gap: 10px;
  margin-bottom: 10px;
}

.category-container {
  grid-column: span 2;
}

.product-form {
  border: gray 1px solid;
  padding: 20px;
  border-radius: 5px;
  display: grid;
  max-width: 400px;
  margin: 40px auto;
}

.form-title {
  margin-top: 15px;
  text-align: center;
  margin-bottom: 10px;
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

label {
  display: grid;
  margin-bottom: 5px;
  text-align: center;
}
</style>