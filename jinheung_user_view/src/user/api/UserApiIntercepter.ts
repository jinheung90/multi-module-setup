import {api} from "../../config/ApiConfig";

api.interceptors.response.use(
    response => {
        console.log("Test")
        return response
    },
    error => {
        if(error.response) {
            let code = error.response.data.code

        }
    }
)