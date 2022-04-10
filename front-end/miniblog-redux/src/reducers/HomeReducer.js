const initialState = {
    currentPage: 1,
    booksPerPage: 6,
    sort: "asc",
    colName: "id",
    search: "",
    numPage: 1,
    currentBooks: [],
    
    idBook: 0,
    errMessage: null,
    book: null
}

const HomeReducer = (state = initialState, action) => {
    switch(action.type){
// khong can check case da vao middleware
        case "onHomeSuccess":
            return {
                ...state,
                currentPage: action.currentPage,
                booksPerPage: action.booksPerPage,
                sort: action.sort,
                colName: action.colName,
                search: action.search,
                numPage: action.numPage,
                currentBooks: action.currentBooks,
            }
        case "getBookSuccess":
            return {
                ...state,
                book: action.book
            }
        case "getBookFailed":
            return {
                ...state,
                errMessage: action.errMessage
            }
        default:
            return state
    }
}
export default HomeReducer;