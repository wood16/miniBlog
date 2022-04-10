import React from 'react';
import { NavLink } from 'react-router-dom';
import './About.css';
const About = () => {
    return(
        <div className='container-about'>
            <h1>About me</h1>
            <p>The theme has a set of icons defined for theme elements in the main functions.php file in your theme files. They are the icon codes from the entypo-fontello font file that is included in the theme to show all of the various icons used throughout the theme.</p>
            <h3>Something else here</h3>
            <p>
            The theme has a set of icons defined for theme elements in the main functions.php file in your theme files. They are the icon codes from the entypo-fontello font file that is included in the theme to show all of the various icons used throughout the theme.
            The theme has a set of icons defined for theme elements in the main functions.php file in your theme files. They are the icon codes from the entypo-fontello font file that is included in the theme to show all of the various icons used throughout the theme.</p>
            <p>
            The theme has a set of icons defined for theme elements in the main functions.php file in your theme files. They are the icon codes from the entypo-fontello font file that is included in the theme to show all of the various icons used throughout the theme.The theme has a set of icons defined for theme elements in the main functions.php file in your theme files. They are the icon codes from the entypo-fontello font file that is included in the theme to show all of the various icons used throughout the theme.
            </p>
            <NavLink to={`/contact`}>
                <button className='btn-about'>Get in touch</button>
            </NavLink>
        </div>  
    ) 
}
export default About;