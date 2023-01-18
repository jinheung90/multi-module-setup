import {api} from '../../config/ApiConfig'
import {LoginState} from "../state/AuthState";
// import {AuthSlice} from "../slice/AuthSlice";

const loginByEmail = async (loginInfo: LoginState) => {
    const response = await api.post("/user/auth/login/", loginInfo)
    return response.data
}