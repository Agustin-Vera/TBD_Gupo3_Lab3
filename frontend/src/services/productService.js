import httpClient from "../Utils/http-common";

const productService = {
  async getProducts() {
    try {
      const response = await httpClient.get("/api/v1/products");
      return response.data;
    } catch (error) {
      throw new Error(
        error.response ? error.response.data : "Error al obtener productos"
      );
    }
  },

  async getProduct(id) {
    try {
      const response = await httpClient.get(`/api/v1/products/${id}`);
      return response.data;
    } catch (error) {
      throw new Error(
        error.response ? error.response.data : "Producto no encontrado"
      );
    }
  },

  async postProduct(product) {
    
    try {
      const response = await httpClient.post("/api/v1/products", product);
      return response.data;
    } catch (error) {
      throw new Error(
        error.response ? error.response.data : "Error al crear producto"
      );
    }
  },

  async putProduct(id,product) {
    
    const category = product.category.id;
    product.category_id = category;
    
    try {
      const response = await httpClient.put(
        `/api/v1/products/${id}`, product);
      return response.data;
    } catch (error) {
      throw new Error(
        error.response ? error.response.data : "Error al actualizar producto"
      );
    }
  },
};

export default productService;
