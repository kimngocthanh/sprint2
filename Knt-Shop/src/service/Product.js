import axios from "axios";
 export const getAllProduct = async (page) => {
    const res = await axios.get(`http://localhost:8080/product?page=${page}`)
    return res;
 }
 export const getProductByName = async (name,page) => {
   const res = await axios.get(`http://localhost:8080/product/get-by-name?page=${page}&searchName=${name}`)
   return res;
 }

 