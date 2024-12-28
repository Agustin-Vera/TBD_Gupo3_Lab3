import httpClient from "../Utils/http-common";


export const querry1 = async (id) => {
    try {
        const response = await httpClient.get(`api/v1/distributors/getByComuna/${id}`);        
        return { data: response.data, status: response.status };
    } catch (error) {
        throw new Error(
        error.response ? error.response.data : "Error al obtener comunas"
        );
    }
};


export const querry2 = async (data) => {
    try {
        
        const response = await httpClient.post("api/v1/comunas/restricted/check", data);
           
        return { data: response.data, status: response.status };
    } catch (error) {
        throw new Error(
        error.response ? error.response.data : "Error al obtener comunas"
        );
    }
};


export const querry3 = async () => {
    
    try {
        const response = await httpClient.get("api/v1/clients/outOfServiceRange");        
        return { data: response.data, status: response.status };
    } catch (error) {
        throw new Error(
        error.response ? error.response.data : "Error al obtener clientes"
        );
    }
};




export const getAllStores = async () => {
    try{
        const response = await httpClient.get("api/v1/stores");
        return { data: response.data, status: response.status };
    }
    catch (error) {
        throw new Error(
        error.response ? error.response.data : "Error al obtener tiendas"
        );
    }
};

