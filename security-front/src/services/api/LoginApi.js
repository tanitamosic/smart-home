import { getApiCall } from "../Configs.js";


export async function sendRegistrationRequest(regRequest){
    try {
        const responseData = await getApiCall().post(`/csr/request`, regRequest);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }