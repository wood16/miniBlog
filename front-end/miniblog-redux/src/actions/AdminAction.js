export function callAdminApi(currentPage, booksPerPage, sort, colName, search){
    return {
        type: "callApi",
        currentPage: currentPage,
        booksPerPage: booksPerPage,
        sort: sort,
        colName: colName,
        search: search
    }
}

export function setDataRespone(dataRes, numPage, currentPage, booksPerPage, sort, colName, search){
    return{
        type: "onSuccess",
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
        type: "getBook",
        idBook: id
    }
}

export function addBook(book){
    return {
        type: "addBook",
        book: book
    }
}

export function updateBook(id, book){
    return {
        type: "updateBook",
        idBook: id,
        book: book
    }
}

export function deleteBooks(deleteIds){
    return {
        type: "deleteBooks",
        deleteIds: deleteIds
    }
}

export function setDeleteIds(deleteId){
    return {
        type: "setDeleteId",
        deleteId: deleteId
    }
}

export function deleteBook(deleteId){
    return {
        type: "deleteBook",
        idBook: deleteId
    }
}



