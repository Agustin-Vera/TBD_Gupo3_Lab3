import httpClient from "../Utils/http-common";


export const AddProductToWishList = async (wishListId, product) => {
    try {
        const response = await httpClient.put(`api/v1/wishList/${wishListId}/addProducts`, product);
        return { data: response.data, status: response.status };
    } catch (error) {
        throw new Error(
            error.response ? error.response.data : "Error al agregar producto a la lista de deseos"
        );
    }
}