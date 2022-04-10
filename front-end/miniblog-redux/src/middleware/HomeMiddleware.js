import axios from "axios";
import {setDataRespone} from "../actions/HomeAction";

const HomeMiddleware = (store) => (next) => async (action) => {

    if (action.type === "callHomeApi"){
        const params = "page="+ action.currentPage + "&pageSize=" + action.booksPerPage 
        + "&sort=" + action.sort + "&colName=" + action.colName + "&search=" + action.search

        console.log("Home param: " + params)

        await axios.get(`http://localhost:8080/v1/books?` + params)
            .then(res => {
                store.dispatch(setDataRespone(res.data.listBook, res.data.numPage, action.currentPage, action.booksPerPage, action.sort, action.colName, action.search))
                window.scrollTo (0,0)
            })
    }
    else if(action.type === "getHomeBook"){
        axios.get(`http://localhost:8080/v1/books/${action.idBook}`)
        .then(res =>{
            store.dispatch({
                type: "getBookSuccess",
                book: res.data
            })
        })
        .catch(err =>{
            // console.log(err.response.data)
            // console.log(err.response.status)
            // console.log(err.response.headers)
            store.dispatch({
                type: "getBookFailed",
                errMessage:  err.response.data.code + " " + err.response.data.description 
            })
            
        })
    }
    else{
        return next(action)
    } 
}
export default HomeMiddleware;