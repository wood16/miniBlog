import { NavLink } from 'react-router-dom';
import Pagination from '../pagination/Pagination';
import './Home.css'
import React, { useEffect} from 'react'

import { callHomeApi} from "../../actions/HomeAction";
import { useSelector, useDispatch } from 'react-redux'


const Home = () => {

    const dispatch = useDispatch();
    const home = useSelector((state) => state.home);

    const setPage = (currentPage, booksPerPage, sort, colName, search) => dispatch(
        callHomeApi(currentPage, booksPerPage, sort, colName, search)) 

    // call api axios
    useEffect(()=>{
        var page = home.currentPage
        // reload in current page
        // get index from url 
        const query = new URLSearchParams(window.location.search);
        const token = query.get('page')
        if(token != null){
            page = token
        }
        dispatch(
            callHomeApi(page, home.booksPerPage, home.sort, home.colName, home.search))
    },[dispatch])

    
    
    const currentBooks = home.currentBooks

    const pages = Math.ceil(home.numPage / home.booksPerPage)
    const list = currentBooks.map((book, i)=>{
        return(
            <div className='row' key={i}>
                <div className='col-3'>
                    <NavLink to={`/books/${book.id}`}>
                        <img src={book.image} alt="loading..." width="200px" height="200px" className='book-img'/>
                    </NavLink>
                </div>
                <div className='col-8'>
                    <NavLink to={`/books/${book.id}`} style={{ textDecoration: 'none' }} className="book-link">
                        <h3 className='book-title'>{book.title}</h3>
                    </NavLink>
                    <p className='book-category'>{book.createDate}{" | "}{book.category.toUpperCase()}</p>
                    <p className='book-description'>{book.description.slice(0,228)}{" "}
                        <NavLink to={`/books/${book.id}`} className="book-link">
                            <span> Read more...</span>
                        </NavLink>
                    </p>
                </div>
            </div>
        )
    })
    return(
        <div className='container-home'>
            {list}
            <Pagination
                data={home}
                numPage={pages}
                type={"home"}
                setPage={setPage}
            />
        </div>  
    )
}
export default Home;