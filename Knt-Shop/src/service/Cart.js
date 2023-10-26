import axios from "axios";

export const productDetail = async (id) => {
    const res = await axios.get(`http://localhost:8080/productDetail/${id}`);
    return res;
}
export const findAllCart = async (username) => {
    const res = await axios.get(`http://localhost:8080/cartDetail?username=${username}`);
    return res
}
export const checkQuantity = async (id,quantity) => {
    const res = await axios.get(`http://localhost:8080/check-quantity?productId=${id}&quantity=${quantity}`);
    return res.status;
}
export const addToCard = async (idProduct,quantity,username) => {
    await axios.post(`http://localhost:8080/add-cart?username=${username}&productId=${idProduct}&quantity=${quantity}`)
}
export const updateToCard = async (idProduct,quantity,username) => {
    await axios.post(`http://localhost:8080/update-cart?username=${username}&productId=${idProduct}&quantity=${quantity}`)
}

export const deleteOneToCard = async (idProduct,quantity,username) => {
    await axios.post(`http://localhost:8080/delete-one-quantity?username=${username}&productId=${idProduct}&quantity=${quantity}`)
}

export const deleteCard = async (idProduct,username) => {
    await axios.delete(`http://localhost:8080/delete-cart?username=${username}&productId=${idProduct}`)
}

export const checkProductInCart = async (id,username) => {
    const res = await axios.get(`http://localhost:8080/check-product-cart?productId=${id}&username=${username}`)
    return res;
}

export const addToCardDetail = async (idProduct,username,quantity) => {
    const res = await axios.get(`http://localhost:8080/add-to-cart?productId=${idProduct}&username=${username}&quantity=${quantity}`);
    console.log(res);
    return res;
}

export const addToOrders = async (cartDetails,username) => {
    console.log(cartDetails);
    const res = await axios.post(`http://localhost:8080/add-to-order?username=${username}`,cartDetails);
    return res;
}

export const findAllOrdersDetail = async(username,code) => {
    const res = await axios.get(`http://localhost:8080/order-detail/${code}?username=${username}`)
    return res;
}