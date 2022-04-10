import React, { useEffect , useState} from 'react';
import { Redirect, useParams } from 'react-router-dom';
import { useHistory } from "react-router-dom";
import "./DetailBlog.css"

import { useSelector, useDispatch } from 'react-redux';
import { getBook, updateBook, addBook, deleteBook }from "../../../actions/AdminAction";


const DetailBlog = () => {
    const {id} = useParams()

    // book 
    const [title, setTitle] = useState(null)
    const [image, setImage] = useState(null)
    const [category, setCategory] = useState(null)
    const [description, setDescription] = useState(null)
    const [createDate, setCreateDate] = useState(null)

    var content =""
    var history = useHistory();

    const dispatch = useDispatch();
    const admin = useSelector((state) => state.admin);
    
    // call api
    useEffect(()=>{
        if(id !== undefined){
            dispatch(getBook(id))
        }
    },[id])

    const book = admin.book
    const errMessage = admin.errMessage

    const data = {
        title: title,
        category: category,
        image: image,
        description: description,
        createDate: createDate
    }
    
    // delete
    const handleDelete = (id) => {
        if(window.confirm("Are you sure you want to delete?")){
            dispatch(deleteBook(id))
        }
    }
    // show and update
    if(book !== null){
        if(title == null){
            setTitle(book.title)
        }
        if(category == null){
            setCategory(book.category)
        }
        if(image == null){
            setImage(book.image)
        }
        if(description == null){
            setDescription(book.description)
        }
        if(createDate == null){
            setCreateDate(book.createDate)
        }

        content = 
        <div className='row detail-blog'>
            <form>
                <div>
                    <label>ID</label>
                    <input type="text" defaultValue={book.id} disabled/>
                </div>
                <div>
                    <label>Image</label>
                    <input type="text" defaultValue={book.image} onChange={e => setImage(e.target.value)}/>
                </div>
                <div>
                    <label>Title</label>
                    <input type="text" defaultValue={book.title} onChange={e => setTitle(e.target.value)}/>
                </div>
                <div>
                    <label>Category</label>
                    <input type="text" defaultValue={book.category} onChange={e => setCategory(e.target.value)}/>
                </div>
                <div>
                    <label>Create Date</label>
                    <input type="text" defaultValue={book.createDate} disabled />
                </div>
                <div>
                    <label>Modified Date</label>
                    <input type="text" defaultValue={book.modifiedDate} disabled/>
                </div>
                <div>
                    <label>Description</label>
                    <textarea cols="93" rows="15" defaultValue={book.description} onChange={e => setDescription(e.target.value)}
                                 style={{fontFamily : "revert"}}
                    ></textarea>
                </div> 
            </form>
            <div className='col'>
                <button className='button btn-back' onClick={history.goBack}>Back</button>
            </div>
            <div className='col'>
            <button className='button btn-update' onClick={() => {  setCreateDate(book.createDate)
                                                                    dispatch(updateBook(book.id, data))}}>Update</button>
            </div>
            <div className='col'>
            <button className='button btn-delete' onClick={() => {handleDelete(book.id)}}>Delete</button>
            </div>
        </div>
    }
    // create
    else{
        content = 
        <div className='row detail-blog'>
            <form>
                <div>
                    <label>Image</label>
                    <input type="text" onChange={e => setImage(e.target.value)} />
                </div>
                <div>
                    <label>Title</label>
                    <input type="text" onChange={e => setTitle(e.target.value)} />
                </div>
                <div>
                    <label>Category</label>
                    <input type="text" onChange={e => setCategory(e.target.value)} />
                </div>
                <div>
                    <label>Description</label>
                    <textarea cols="93" rows="15" onChange={e => setDescription(e.target.value)} style={{fontFamily : "revert"}}></textarea>
                </div> 
            </form>
            <div className='col'>
                <button className='button btn-back' onClick={history.goBack}>Back</button>
            </div>
            <div className='col'>
            <button className='button btn-update' onClick={() => {dispatch(addBook(data))}}>Create</button>
            </div>
        </div>
    }
    if(errMessage !== null){
        content = 
        <h1>
            {errMessage}
        </h1>
    }
    if(admin.redirect){
        const param = "/admin?page=" + admin.currentPage;
        return <Redirect to={param} />;
    }
    return(
        <div className='container-detail-blog'>
            <h1>Detail Blog</h1>
            {content}
        </div>   
    )   
}
export default DetailBlog;