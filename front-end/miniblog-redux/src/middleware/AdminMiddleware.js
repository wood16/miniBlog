import axios from "axios";
import {setDataRespone} from "../actions/AdminAction";

const AdminMiddleware = (store) => (next) => async (action) => {
    // get books
    if (action.type === "callApi"){
        const params = "page="+ action.currentPage + "&pageSize=" + action.booksPerPage 
        + "&sort=" + action.sort + "&colName=" + action.colName + "&search=" + action.search

        console.log("Admin param: " + params)

        await axios.get(`http://localhost:8080/v1/books?` + params)
            .then(res => {
                store.dispatch(setDataRespone(res.data.listBook, res.data.numPage, action.currentPage, action.booksPerPage, action.sort, action.colName, action.search))
                window.scrollTo (0,0)
            })
    }
    // get book
    else if(action.type === "getBook"){
        axios.get(`http://localhost:8080/v1/books/${action.idBook}`)
        .then(res =>{
            store.dispatch({
                type: "getSuccess",
                book: res.data
            })
        })
        .catch(err =>{
            store.dispatch({
                type: "getFailed",
                errMessage:  err.response.data.code + " " + err.response.data.description 
            })
            
        })
    }
    // add book
    else if(action.type === "addBook"){
        axios.post('http://localhost:8080/v1/books', action.book)
        .then(res => {
            alert("Create Success")
            store.dispatch({
                type: "addSuccess",
                redirect: true
            })
        }).catch(err => {
            store.dispatch({
                type: "getFailed",
                errMessage:  err.response.data.code + " " + err.response.data.description 
            })
        });
    }
    // update book
    else if(action.type === "updateBook"){
        axios.put(`http://localhost:8080/v1/books/${action.idBook}`, action.book)
        .then(res => {
            alert("Update Success")
            store.dispatch({
                type: "updateSuccess",
                redirect: true
            })
        }).catch(err => {
            store.dispatch({
                type: "getFailed",
                errMessage:  err.response.data.code + " " + err.response.data.description 
            })
        });

    }
    // delete books
    else if(action.type === "deleteBooks"){     
        const admin = store.getState().admin
        const params = "ids=" + action.deleteIds.map(item=>item).join("&ids=")
        axios.delete(`http://localhost:8080/v1/books?` + params)
        .then(res => {
            // alert("Delete Success")
            store.dispatch({
                type: "callApi",
                currentPage: admin.currentPage,
                booksPerPage: admin.booksPerPage,
                sort: admin.sort,
                colName: admin.colName,
                search: admin.search
            })
        }).catch(err => {
            console.log(err)
        });
    }
    // delete book
    else if(action.type === "deleteBook"){     
        axios.delete(`http://localhost:8080/v1/books?ids=` + action.idBook)
        .then(res => {
            store.dispatch({
                type: "deleteSuccess",
                redirect: true
            })
        }).catch(err => {
            console.log(err)
        });
    }
    else{
        return next(action)
    } 
}
export default AdminMiddleware;