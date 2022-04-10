import { NavLink } from 'react-router-dom';
const Author = () => {
    return(
        <div className='container-author'>
            <NavLink to={`/admin`}><img src='https://i.pinimg.com/originals/56/f0/c7/56f0c7de57fdae6d0a9ddc43448b6dff.png' alt='Loading...' width='150px' height='150px' className='img-author'/></NavLink>
            <div className='text-author'>
                <p>Denali is a simple impress blog template. Easily add new posts using the Editor or change layout and design using the Designer.</p>
                <hr/>
                <p>Featured Posts:</p>
                <div className='feature-post'>
                <p>According a funnily until pre-set or arrogant well cheerful</p>
                <p>Overlaid the jeepers uselessly much excluding</p>
                <hr/>
                <a href="#" className="fa fa-facebook"></a>
                <a href="#" className="fa fa-twitter"></a>
                <a href="#" className="fa fa-google"></a>
                <a href="#" className="fa fa-linkedin"></a>
                <p>BUILD WITH WEBFLOW </p>
                </div>
            </div>
        </div>
    )
}
export default Author;