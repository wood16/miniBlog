
export function callHomeApi(currentPage, booksPerPage, sort, colName, search){
    return {
        type: "callHomeApi",
        currentPage: currentPage,
        booksPerPage: booksPerPage,
        sort: sort,
        colName: colName,
        search: search
    }
}

export function setDataRespone(dataRes, numPage, currentPage, booksPerPage, sort, colName, search){
    return{
        type: "onHomeSuccess",
        currentBooks: dataRes,
        numPage: numPage,
        
        currentPage: currentPage,
        booksPerPage: booksPerPage,
        sort: sort,
        colName: colName,
        search: search
    }
}

export function getBook(id){
    return {
        type: "getHomeBook",
        idBook: id
    }
}