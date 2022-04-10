package com.bookshop.poc.service.mapper;

import com.bookshop.poc.service.entity.BookEntity;
import com.bookshop.poc.service.mock.BookData;
import com.bookshop.poc.service.repository.BookRepository;
import com.miniblog.poc.api.model.Book;
import com.miniblog.poc.api.model.BookReq;
import com.miniblog.poc.api.model.Books;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookMapperTest {

    @InjectMocks
    BookMapper bookMapper;

    @Mock
    BookRepository bookRepository;

    @Test
    public void ensureMapBookResFromBookEntity(){
        BookEntity bookEntity = BookData.mockBookEntity();
        Book book = bookMapper.mapBookResFromBookEntity(bookEntity);

        assertBook(book, bookEntity);
    }

    @Test
    public void ensureMapBookEntityFromBookReqForAdd(){
        BookReq bookReq = BookData.mockBookReq();
        BookEntity bookEntity = bookMapper.mapBookEntityFromBookReq(bookReq);

        assertThat(bookEntity.getCategory(), is(bookReq.getCategory()));
        assertThat(bookEntity.getDescription(), is(bookReq.getDescription()));
        assertThat(bookEntity.getImage(), is(bookReq.getImage()));
        assertThat(bookEntity.getTitle(), is(bookReq.getTitle()));
        assertThat(bookEntity.getCreateDate() != null, is(true));
    }

    @Test
    public void ensureMapBookEntityFromBookReqForUpdate(){
//        test getOne
        BookEntity entity = BookData.mockBookEntity();
        when(bookRepository.getOne(BookData.ID)).thenReturn(entity);
//        test mapper
        BookReq bookReq = BookData.mockBookReq();
        BookEntity bookEntity = bookMapper.mapBookEntityFromBookReq(BookData.ID, bookReq);

        assertThat(bookEntity.getTitle(), is(bookReq.getTitle()));
        assertThat(bookEntity.getImage(), is(bookReq.getImage()));
        assertThat(bookEntity.getDescription(), is(bookReq.getDescription()));
        assertThat(bookEntity.getCategory(), is(bookReq.getCategory()));

        assertThat(bookEntity.getId(), is(entity.getId()));
        assertThat(bookEntity.getCreateDate(), is(entity.getCreateDate()));
        assertThat(bookEntity.getModifiedDate() != null, is(true));
    }

    @Test
    public void ensureMapBookListFromBookEntities(){
        List<BookEntity> bookEntities = BookData.mockBookEntities();
        Books books = bookMapper.mapBookListFromBookEntities(bookEntities, BookData.NUMPAGE);

        assertBooks(books, bookEntities);
    }

    //    check field after mapper
    private void assertBook(Book actual, BookEntity input){
        assertThat(actual.getModifiedDate(), is(input.getModifiedDate()));
        assertThat(actual.getTitle(), is(input.getTitle()));
        assertThat(actual.getImage(), is(input.getImage()));
        assertThat(actual.getCategory(), is(input.getCategory()));
        assertThat(actual.getCreateDate(), is(input.getCreateDate()));
        assertThat(actual.getDescription(), is(input.getDescription()));
        assertThat(actual.getId(), is(input.getId()));
    }

    private void assertBooks(Books actual, List<BookEntity> input){
        assertBook(actual.getListBook().get(0), input.get(0));
    }
}
