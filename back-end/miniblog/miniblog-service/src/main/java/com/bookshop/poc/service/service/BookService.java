package com.bookshop.poc.service.service;

import com.bookshop.poc.service.entity.BookEntity;
import com.bookshop.poc.service.repository.BookRepository;
import com.bookshop.poc.service.search.BookSearch;
import com.bookshop.poc.service.search.HibernateSearchUtil;
import com.bookshop.poc.service.mapper.BookMapper;
import com.miniblog.poc.api.model.Book;
import com.miniblog.poc.api.model.BookReq;
import com.miniblog.poc.api.model.Books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookService {

    private static final int MIN_LENGTH = 0;

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final BookSearch bookSearch;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper, BookSearch bookSearch) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.bookSearch = bookSearch;
    }

    public Books findAll(Integer page, Integer pageSize, String sort, String colName, String search) {
        List<BookEntity> bookEntities;
//          search
        if(search.isEmpty()){
            bookEntities = bookRepository.findAll();
        }else{
            search = HibernateSearchUtil.decodeUrl(search);
            bookEntities = bookSearch.searchBook(search);
        }
//          sort
        bookEntities = sortBookEntities(colName, sort, bookEntities);
//          paging
        int indexOfLast = page * pageSize;
        int indexOfFirst = indexOfLast - pageSize;
        int numPage = bookEntities.size();

        if (indexOfLast > bookEntities.size()) {
            indexOfLast = bookEntities.size();
        }

        if (indexOfFirst < 0 || indexOfFirst > indexOfLast) {
            return null;
        }

        return bookMapper.mapBookListFromBookEntities(bookEntities.subList(indexOfFirst, indexOfLast), numPage);
    }

    public Book findById(Long id) {
        BookEntity bookEntity = bookRepository.getOne(id);
        return bookMapper.mapBookResFromBookEntity(bookEntity);
    }

    public Book save(BookReq bookReq) {
        BookEntity bookEntity = bookRepository.save(bookMapper.mapBookEntityFromBookReq(bookReq));
        return bookMapper.mapBookResFromBookEntity(bookEntity);
    }

    public Book update(Long id, BookReq bookReq) {
        BookEntity bookEntity = bookMapper.mapBookEntityFromBookReq(id, bookReq);
        return bookMapper.mapBookResFromBookEntity(bookRepository.save(bookEntity));
    }

    public void deletes(List<Long> ids) {
        if(ids.size() > MIN_LENGTH ){
            for(Long id : ids) {bookRepository.deleteById(id);}
        }
    }

    private List<BookEntity> sortBookEntities(String colName, String sort, List<BookEntity> bookEntities){
//        sort date
        if (colName.equalsIgnoreCase("createdate")) {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Comparator<BookEntity> compareByDate =
                    (BookEntity o1, BookEntity o2) -> {
                        try {
                            return df.parse(o1.getCreateDate()).compareTo(df.parse(o2.getCreateDate()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    };
            if (sort.equalsIgnoreCase("desc")) {
                Collections.sort(bookEntities, compareByDate.reversed());
            } else {
                Collections.sort(bookEntities, compareByDate);
            }
        }
//        sort id
        if (colName.equalsIgnoreCase("id")) {
            if (sort.equalsIgnoreCase("desc")) {
                Collections.sort(bookEntities, Comparator.comparing(BookEntity::getId).reversed());
            }else{
                Collections.sort(bookEntities, Comparator.comparing(BookEntity::getId));
            }
        }
        return bookEntities;
    }
}
