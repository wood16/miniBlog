import React, { useEffect } from 'react';
import { useHistory, useParams} from "react-router-dom";
import './Detail.css';

import { getBook} from "../../../actions/HomeAction";
import { useSelector, useDispatch } from 'react-redux'

const Detail = () => {

    const {id} = useParams()

    var content =""
    var history = useHistory();

    const dispatch = useDispatch();
    const home = useSelector((state) => state.home);

    // call api
    useEffect(() => {
        dispatch(getBook(id));
    }, [id])

    const book = home.book
    const errMessage = home.errMessage

    if(book !== null){
        content = 
        <div className='detail-content'>
            <img src={book.image} alt='Loading...' width='100%' height='450px' />
            <h1>{book.title}</h1>
            <div className='detail-title'>
                <p>{book.createDate} {" | "} {book.category.toUpperCase()}</p>
                <hr/>
            </div>
            <p>{book.description}</p>
            <button className='button-back' onClick={history.goBack}>Back</button>
        </div>
    }
    if(errMessage !== null){
        content = 
        <h1>
            {errMessage}
        </h1>
    }
    
    return(
        <div className='container-detail'>
            {content}   
        </div>  
    )
    
}
export default Detail;