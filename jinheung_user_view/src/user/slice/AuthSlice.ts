import { createSlice } from '@reduxjs/toolkit'
import type { PayloadAction } from '@reduxjs/toolkit'

import {LoginState} from "../state/AuthState";


const initState : LoginState = {
    email: "",
    password : "",
}
//
export const AuthSlice = createSlice({
    name: 'AuthSlice',
    initState,
    reducers: {
        login: (state, action: PayloadAction<LoginState>) => {
            state.name
        }
    },
})
