import { NavLink } from 'react-router-dom'

const Pagination = (props) => {

    const numPage = props.numPage
    const type = props.type
    const data = props.data

    var page
    if(numPage !== 0){
        // tao mang tu 1..N
        var temp = Array.from({length: numPage}, (_, i) => i + 1)
        page = temp.map((item, i)=>{
            return(             
                <li className="page-item" key={item}>
                    <NavLink to={`/${type}?page=${item}`} className="page-link" 
                    onClick={() => {props.setPage(item, data.booksPerPage, data.sort, data.colName, data.search)}}>
                        {item}
                    </NavLink>
                </li>
            ) 
        })
    }
    return(
        <div className='body-pagigation'>
            <nav aria-label="Page navigation example">
                <ul className="pagination">
                    {page}
                </ul>
            </nav>
        </div>
    )
}
export default Pagination;