import Axios from 'axios';
import {ErrorMap} from "../error/ErrorMap";
import {ErrorCode} from "../error/ErrorCode";


export const api = Axios
    .create(
        {
            baseURL: 'http://localhost:8080/api',
            withCredentials : true
        }
    )
api.interceptors.response.use(
    function (response) {
        if(response.status >= 400) {
            let customCode = response.data.code
            let errorCode =  ErrorMap.getErrorCode(customCode)
            alert(errorCode.message)
        }
        return response;
    },
    function (error) {
        return Promise.reject(error);
    });

api.defaults.headers[]