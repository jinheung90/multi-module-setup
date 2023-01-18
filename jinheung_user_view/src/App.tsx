import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Routes} from "react-router-dom";

const App = () =>  {
  return (
    <div className="App">
      <BrowserRouter>

        {/* eslint-disable-next-line react/jsx-no-undef */}
        <Routes>

          {/*<Route path="/user/*" element={<Product />}></Route>*/}

        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
