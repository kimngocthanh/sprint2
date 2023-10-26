import axios from "axios";
import jwt_decode from "jwt-decode";

export const loginUsername = async (authenticationRequest) => {
    const res = await axios.post(`http://localhost:8080/authenticate`, authenticationRequest)
    return res;
}

export const registerUsername = async (registerRequest) => {
    const res = await axios.post(`http://localhost:8080/register`, registerRequest)
    return res;
}

export const infoAppUserByJwtToken = () => {
    const jwtToken = localStorage.getItem("JWT");
    if(jwtToken != null) {
        const decodedToken = jwt_decode(jwtToken);
        return decodedToken;
    }  else{
        return null;
    };
}


