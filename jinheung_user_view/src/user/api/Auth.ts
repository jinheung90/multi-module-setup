import {api} from '../../config/ApiConfig'
import {LoginState} from "../state/AuthState";
// import {AuthSlice} from "../slice/AuthSlice";

const prefix : string = "/auth"

const loginByEmail = async (loginInfo: LoginState) => {
    const response = await api.post()
    return response.data
}