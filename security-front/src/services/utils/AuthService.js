import jwtDecode from "jwt-decode";

export const setToken = (token) => {
  if(token === null){
    sessionStorage.setItem("jwt", null);
  }
  else{
    sessionStorage.setItem("jwt", JSON.stringify(token));
  }
}

export const getToken = () => {
   const token = sessionStorage.getItem("jwt");
   
   if (!!token){
    return token;
  } else {
    return "";
  }
}

export const getTokenWithNoQuotes = () => {
  const token = sessionStorage.getItem("jwt");
  
  if (!!token){
    const splitedtoken = token.slice(1, -1);
   return splitedtoken;
 } else {
   return "";
 }
}

export const removeToken = () => {
  return sessionStorage.removeItem("jwt");
}

export const removeLoggedUserData = () => {
  removeToken();
  sessionStorage.removeItem("role");
  sessionStorage.removeItem("email");
}

export const getLoggedUserEmail = () => {
  const email = sessionStorage.getItem("email");

  return email;
}

export const getRole = () => {
  const role = sessionStorage.getItem("role");

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

