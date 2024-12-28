import httpClient from "../Utils/http-common";


export const getAllDistributors = async () => {

    try {
        const response = await httpClient.get("api/v1/distributors");
        return { data: response.data, status: response.status };
    } catch (error) {
        throw new Error(
            error.response ? error.response.data : "Error al obtener distribuidores"
        );
    }
};