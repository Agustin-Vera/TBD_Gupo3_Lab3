import httpClient from "../Utils/http-common";




export const getLogs = async () => {
    try{
        const response = await httpClient.get("api/v1/logs");
        return { data: response.data, status: response.status };
    }
    catch (error) {
        throw new Error(
        error.response ? error.response.data : "Error al obtener logs"
        );
    }
};