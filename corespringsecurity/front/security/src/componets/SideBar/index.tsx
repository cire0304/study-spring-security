import React, { useState } from "react";
import { Nav } from "react-bootstrap";
// import { withRouter } from "react-router";

import './style.css'

import { Routes, Route, Link, useNavigate, Outlet } from 'react-router-dom';

const SideBar = () => {

    let navigete = useNavigate();


    return (
        <>
            <Nav id="sidevar" className={`col-md-12 d-none d-md-block bg-light`}>
                <div className="sidebar-sticky"></div>
           
                <Nav.Item className="mynav">
                    <Nav.Link onClick={() => navigete("sdf")} >대시보드</Nav.Link>
                </Nav.Item>
                <Nav.Item className="mynav">
                    <Nav.Link onClick={() => navigete("/mypage")} >마이페이지</Nav.Link>
                </Nav.Item>
                <Nav.Item className="mynav">
                    <Nav.Link onClick={() => navigete("/message")} >메시지</Nav.Link>
                </Nav.Item>
                <Nav.Item className="mynav">
                    <Nav.Link onClick={() => navigete("sdf")} >환경설정</Nav.Link>
                </Nav.Item>
            
            </Nav>
        </>
    );
};

export default SideBar