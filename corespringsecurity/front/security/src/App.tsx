import "bootstrap/dist/css/bootstrap.min.css";
import React from 'react';
import logo from './logo.svg';
import './App.css';

import NavBar from './componets/NavBar'

import { Routes, Route, Link, useNavigate, Outlet } from 'react-router-dom';
import SideBar from "./componets/SideBar";
import LoginFrom from "./pages/login"
import Mypage from "./pages/mypage";
import Message from "./pages/message";


function App() {



  return (
    <div className="App">
      <NavBar />
      <div className="layout">

        <aside style={{ gridArea: 'side' }}>
          <SideBar />
        </aside>

        <main style={{ gridArea: 'main' }}>
          <Routes>
            <Route path="/" element={<div>home</div>}/>
            <Route path="/users" element={<LoginFrom/>}/>
            <Route path="/mypage" element={<Mypage/>}/>
            <Route path="/message" element={<Message/>}/>
          </Routes>
        </main>
        <footer style={{ gridArea: 'footer' }}>footer</footer>
      </div>




    </div>
  );
}

export default App;
