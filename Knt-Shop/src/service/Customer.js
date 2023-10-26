import axios from "axios";

export const getCustomerDetail =async (username)=> {
    const res = await axios.get(`http://localhost:8080/customer/detail?username=${username}`)
    return res;
}

export const createCustomer = async (customer,username) => {
    await axios.post(`http://localhost:8080/customer/add?username=${username}`,customer)
}