const Admin = (state = {
    currentPage: 1,
    booksPerPage: 8,
    sort: "asc",
    colName: "id",
    search: "",
    numPage: 1,
    currentBooks: [],
    
    deleteIds: [],
    idBook: 0,
    errMessage: null,
    book: null,
    redirect: false,
}, action) => {
    switch(action.type){       
        case "onSuccess":
            return {
                ...state,
                currentPage: action.currentPage,
                booksPerPage: action.booksPerPage,
                sort: action.sort,
                colName: action.colName,
                search: action.search,
                numPage: action.numPage,
                currentBooks: action.currentBooks,
                // reset book 
                book: null,
                redirect: false,
                deleteIds: []
            }
        case "getSuccess":
            return {
                ...state,
                book: action.book
            }
        case "addSuccess":
            return {
                ...state,
                redirect: action.redirect
            }
        case "updateSuccess":
            return {
                ...state,
                redirect: action.redirect
            }
        case "deleteSuccess":
            return {
                ...state,
                idBook: 0,
                redirect: action.redirect
            }
        case "getFailed":
            return {
                ...state,
                errMessage: action.errMessage
            }
        case "setDeleteId":
            return {
                ...state,
                deleteIds: [...state.deleteIds, action.deleteId] 
            }
        case "clearIds":
            return {
                ...state,
                deleteIds: [],
            }
        default:
            return state
    }
}
export default Admin;