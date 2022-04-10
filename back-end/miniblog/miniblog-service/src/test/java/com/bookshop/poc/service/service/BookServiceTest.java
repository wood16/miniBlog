package com.bookshop.poc.service.service;

import com.bookshop.poc.service.mapper.BookMapper;
import com.bookshop.poc.service.mock.BookData;
import com.bookshop.poc.service.repository.BookRepository;
import com.bookshop.poc.service.search.BookSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    BookMapper bookMapper;

    @Mock
    BookSearch bookSearch;

    @Test
    public void testFindAll(){
        bookService.findAll(1, 8,"asc", "id", "music");
    }

    @Test
    public void testFindAllOtherSort(){
        bookService.findAll(1,8,"desc","id", "music");
    }

    @Test
    public void testFindAllOtherColName(){
        bookService.findAll(1,8,"asc","createDate", "music");
    }

    @Test
    public void testFindAllOtherSortAndColName(){
        bookService.findAll(1,8,"desc","createDate", "music");
    }

    @Test
    public void testFindById(){
        bookService.findById(BookData.ID);
    }

    @Test
    public void testSave(){
        bookService.save(BookData.mockBookReq());
    }

    @Test
    public void testUpdate(){
        bookService.update(BookData.ID, BookData.mockBookReq());
    }

    @Test
    public void testDeletes(){
        bookService.deletes(BookData.IDS);
    }

}
