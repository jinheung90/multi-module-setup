import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import type { PayloadAction } from '@reduxjs/toolkit'

import {LoginState} from "../state/AuthState";
import { api } from "../../config/ApiConfig";


const fetchAuth = createAsyncThunk(
    '/auth/login',
    async (loginInfo: LoginState) => {
        const response = await api.post("/user/auth/login/", loginInfo)
    }
)