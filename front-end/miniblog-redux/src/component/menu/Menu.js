import React from 'react';
import { NavLink } from 'react-router-dom';
const Menu = () => {
    return(
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className='container'>
                <div className="container d-flex flex-grow-1">
                    <NavLink className="navbar-brand d-none d-lg-inline-block" to="/">HoangLam</NavLink>
                </div>
                <div className="container collapse navbar-collapse flex-grow-1 text-right" id="myNavbar">
                    <ul className="nav navbar-nav ml-auto w-100 justify-content-end">
                        <li className="nav-item">
                            <NavLink exact className="nav-link" to="/">Home<span className="sr-only">(current)</span></NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink className="nav-link" to="/about">About</NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink className="nav-link" to="/contact">Contact</NavLink>
                        </li>
                    </ul>
                </div>
            </div>  
        </nav>
    )
}
export default Menu;