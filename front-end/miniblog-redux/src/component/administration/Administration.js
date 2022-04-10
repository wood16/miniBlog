import React, {useEffect, useState} from 'react'
import Pagination from '../pagination/Pagination';
import "./Admin.css"
import { NavLink } from 'react-router-dom';

import {callAdminApi, setDeleteIds, deleteBooks} from "../../actions/AdminAction";
import { useSelector, useDispatch } from 'react-redux'

const Administration = () => {
    
    const dispatch = useDispatch();
    const admin = useSelector((state) => state.admin);
    const [search, setSearch] = useState("")

    const setPage = (currentPage, booksPerPage, sort, colName, search) => dispatch(
        callAdminApi(currentPage, booksPerPage, sort, colName, search)) 

    // call api axios
    useEffect(()=>{
        var page = admin.currentPage
        // reload in current page
        // get index from url 
        const query = new URLSearchParams(window.location.search);
        const token = query.get('page')
        if(token != null){
            page = token
        }
        dispatch(
            callAdminApi(page, admin.booksPerPage, admin.sort, admin.colName, admin.search))
    },[dispatch])

    
    // xoa nhieu
    const handleDelete = (arrIds) =>{
        if(arrIds.length !== 0){
            if(window.confirm("Are you sure you want to delete?")){
                dispatch(deleteBooks(arrIds))
            }
            else{
                dispatch({
                    type: "clearIds"
                })
            }
        }else{
            alert("Select Blog!!!")
        }
    }
    // Check id  
    function checkId(ids,id){
        return ids.some(function(arrVal) {
            return id === arrVal;
        })
    }

    const currentBooks = admin.currentBooks
    const pages = Math.ceil(admin.numPage / admin.booksPerPage)
    const list = currentBooks.map((book, i)=>{
        return(
            <tr key={i}>
                <td>
                <input type="checkbox" id={book.id} name="checkbox" value={book.id} checked={checkId(admin.deleteIds, book.id)}
                                                                        onChange={()=> {dispatch(setDeleteIds(book.id))}}/>
                </td>
                <td>{book.id}</td>
                <td>{book.title}</td>
                <td>{book.category}</td>
                <td>{book.createDate}</td>
                <td className='description'>{book.description}</td>
                <td><NavLink to={`/admin/books/${book.id}`}><button className='button' >Detail</button></NavLink></td>
            </tr>
        )
    })
    return(
        <div className='body-admin'>
            <div className='title-admin'>
                <h3>Admin blogs</h3>
                <NavLink to={`/admin/add`}>
                    <button className='button-add'> + Add Blog</button>
                </NavLink>
            </div>
            <div className='search-admin'>
                <label className='col-sm-2'>Title</label>
                <input className='col-sm-4' type="text" placeholder="title..." onChange={e => setSearch(e.target.value)}/>
                <NavLink to={`/admin`} className="col-sm-2"><button className='button' onClick={()=>{
                    dispatch(callAdminApi(1, admin.booksPerPage, admin.sort, admin.colName, search))}}>Search</button>
                </NavLink>
            </div>
            <table>
            <tbody>
                <tr>
                    <th>
                        <button className='button-delete' onClick={() => handleDelete(admin.deleteIds)}>Delete</button>
                    </th>
                    <th>ID  
                        <button className='btn-arrow-up' 
                            onClick={()=> {dispatch(callAdminApi(admin.currentPage, admin.booksPerPage, "desc", "id", admin.search))}}>
                                <i className="fas fa-caret-square-up"></i>
                        </button>  
                        <button className='btn-arrow-down'
                            onClick={()=> {dispatch(callAdminApi(admin.currentPage, admin.booksPerPage, "asc", "id", admin.search))}}>
                                <i className="fas fa-caret-square-down"></i>
                        </button>
                    </th>
                    <th>TITLE</th>
                    <th>CATEGORY</th>
                    <th>CREATE DATE
                        <button className='btn-arrow-up' 
                            onClick={()=> {dispatch(callAdminApi(admin.currentPage, admin.booksPerPage, "desc", "createDate", admin.search))}}>
                            <i className="fas fa-caret-square-up"></i>
                        </button>  
                        <button className='btn-arrow-down'
                            onClick={()=> {dispatch(callAdminApi(admin.currentPage, admin.booksPerPage, "asc", "createDate", admin.search))}}>
                            <i className="fas fa-caret-square-down"></i>
                        </button>
                    </th>
                    <th>DESCRIPTION</th>
                    <th>ABOUT</th>
                </tr>
                {list}
            </tbody>
            </table>
            <Pagination
                data={admin}
                setPage={setPage}
                numPage={pages}
                type={"admin"}
            />
        </div>
    )
}
export default Administration;