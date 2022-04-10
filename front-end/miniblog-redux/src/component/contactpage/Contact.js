import React from 'react';
import { NavLink } from 'react-router-dom';
import './Contact.css';
const Contact = () => {
    return(
        <div className='container-contact'>
            <h1>Get in touch</h1>
            <p>
            The theme has a set of icons defined for theme elements in the main functions.php file in your theme files. They are the icon codes from the entypo-fontello font file that is included in the theme to show all of the various icons used throughout the theme.
            The theme has a set of icons defined for theme elements in the main functions.php file in your theme files. They are the icon codes from the entypo-fontello font file that is included in the theme to show all of the various icons used throughout the theme.
            </p>
            <p>
            The theme has a set of icons defined for theme elements in the main functions.php file in your theme files. They are the icon codes from the entypo-fontello font file that is included in the theme to show all of the various icons used throughout the theme.
            The theme has a set of icons defined for theme elements in the main functions.
            </p>
            <div className='form-contact'>
                <div className='form-input'>
                    <h5>Name</h5>
                    <input type="text" id="name" name="name" placeholder="Enter your name"></input>
                </div>
                <div>
                    <h5>Email Address</h5>
                    <input type="text" id="email" name="email" placeholder="Enter your email address"></input>
                </div>
                <div>
                    <h5>Message</h5>
                    <input type="text" id="message" name="message" placeholder="Enter your message"></input>
                </div>
                <NavLink to={`/home`}>
                    <button className='btn-contact' onClick={()=>{ alert('Submit Success'); }}>Submit</button>
                </NavLink>
            </div>
        </div>  
    )
}
export default Contact;