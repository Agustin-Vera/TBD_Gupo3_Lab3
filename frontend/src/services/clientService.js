import httpClient from "../Utils/http-common";

export const postClient = async (userData) => {
  try {
    const response = await httpClient.post("auth/register", userData);
    return { data: response.data, status: response.status };
  } catch (error) {
    if (error.response) {
      console.error("Error en la respuesta del servidor:", error.response.data);
      return { data: error.response.data, status: error.response.status };
    } else if (error.request) {
      console.error("No se recibió respuesta del servidor:", error.request);
    } else {
      console.error("Error al hacer la solicitud:", error.message);
    }
    throw error;
  }
};

export const loginUser = async (userData) => {
  try {
    const response = await httpClient.post("auth/login", userData);
    return { data: response.data, status: response.status };
  } catch (error) {
    if (error.response) {
      console.error("Error en la respuesta del servidor:", error.response.data);
      return { data: error.response.data, status: error.response.status };
    } else if (error.request) {
      console.error("No se recibió respuesta del servidor:", error.request);
    } else {
      console.error("Error al hacer la solicitud:", error.message);
    }
    throw error;
  }
};

export const logoutUser = async () => {
  try {
    const response = await httpClient.post("auth/logout");
    return { data: response.data, status: response.status };
  } catch (error) {
    if (error.response) {
      console.error("Error en la respuesta del servidor:", error.response.data);
      return { data: error.response.data, status: error.response.status };
    } else if (error.request) {
      console.error("No se recibió respuesta del servidor:", error.request);
    } else {
      console.error("Error al hacer la solicitud:", error.message);
    }
    throw error;
  }
};

export const getUser = async (id) => {
  try {
    const response = await httpClient.get(`api/v1/clients/${id}`);
    return { data: response.data, status: response.status };
  } catch (error) {
    if (error.response) {
      console.error("Error en la respuesta del servidor:", error.response.data);
      return { data: error.response.data, status: error.response.status };
    } else if (error.request) {
      console.error("No se recibió respuesta del servidor:", error.request);
    } else {
      console.error("Error al hacer la solicitud:", error.message);
    }
    throw error;
  }
}


export const getAllUsers = async () => {
  try {
    const response = await httpClient.get("api/v1/clients");
    return { data: response.data, status: response.status };
  } catch (error) {
    if (error.response) {
      console.error("Error en la respuesta del servidor:", error.response.data);
      return { data: error.response.data, status: error.response.status };
    } else if (error.request) {
      console.error("No se recibió respuesta del servidor:", error.request);
    } else {
      console.error("Error al hacer la solicitud:", error.message);
    }
    throw error;
  }
}
