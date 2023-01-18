
import * as React from 'react';
import Axios from 'axios';
export const api = Axios
    .create(
        {
            baseURL: 'http://localhost:8081/api'
        }
    );



