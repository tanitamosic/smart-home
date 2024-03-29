import jwtDecode from "jwt-decode";
import store from '../../secureStore/store';

export const getToken = () => {
  const state = store.getState();
  const token = state.auth.token;
   
   if (!!token){
    return token;
  } else {
    return "";
  }
}

export const getTokenWithNoQuotes = () => {
  const token = getToken();
  return token;
//   if (!!token){
//     const splitedtoken = token.slice(1, -1);
//    return splitedtoken;
//  } else {
//    return "";
//  }
}

export const getLoggedUserEmail = () => {
  const decodedToken = getDecodedToken();
  const email = decodedToken.sub;

  return email;
}

export const getRole = () => {
  const decodedToken = getDecodedToken();
  const role = decodedToken.role;

  if (role === "ROLE_ADMIN"){
    return "admin";
  } else if (role === "ROLE_OWNER" || role === "ROLE_RENTER"){
    return "client";
  } else {
    return "";
  }
}

export const getDecodedToken = () => {
  const token = getToken();

  if (!!token){
    return jwtDecode(token);
  } else {
    return {};
  }
}

export const getCookie = () => {
  const state = store.getState();
  const cookie = state.auth.cookie;

  console.log("auth service cooke")
  console.log(cookie)
   
   if (!!cookie){
    return cookie;
  } else {
    return "";
  }
}