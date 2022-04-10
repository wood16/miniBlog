package com.bookshop.poc.service.api;

import com.bookshop.poc.service.mock.BookData;
import com.bookshop.poc.service.service.BookService;
import com.bookshop.poc.service.validator.BookSearchValidator;
import com.bookshop.poc.service.validator.BookValidator;
import com.miniblog.poc.api.model.Book;
import com.miniblog.poc.api.model.BookReq;
import com.miniblog.poc.api.model.Books;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @InjectMocks
    BookController bookController;

    @Mock
    BookService bookService;

    @Mock
    BookSearchValidator bookSearchValidator;

    @Mock
    BookValidator bookValidator;

    @Test
    public void testEndpointGetBooks(){
        when(bookService.findAll(1, 8, "asc", "id", "name"))
                .thenReturn(BookData.mockBooks());
        ResponseEntity<Books> responseEntity = bookController.getBooks(1, 8, "asc", "id", "name");

        assertBooks(responseEntity.getBody());
        assertStatus200(responseEntity.getStatusCode());
    }

    @Test
    public void testEndpointGetBookById(){
        when(bookService.findById(anyLong()))
                .thenReturn(BookData.mockBook());
        ResponseEntity<Book> responseEntity = bookController.getBookById(BookData.ID);

        assertBook(responseEntity.getBody());
        assertStatus200(responseEntity.getStatusCode());
    }

    @Test
    public void testEndpointSaveBook(){
        when(bookService.save(any(BookReq.class)))
                .thenReturn(BookData.mockBook());
        ResponseEntity<Book> responseEntity = bookController.saveBook(BookData.mockBookReq());

        assertBook(responseEntity.getBody());
        assertStatus200(responseEntity.getStatusCode());
    }

    @Test
    public void testEndpointUpdateBook(){
        when(bookService.update(anyLong(), any(BookReq.class)))
                .thenReturn(BookData.mockBook());
        ResponseEntity<Book> responseEntity = bookController.updateBook(BookData.ID, BookData.mockBookReq());

        assertBook(responseEntity.getBody());
        assertStatus200(responseEntity.getStatusCode());
    }

    @Test
    public void testEndpointDeleteBooks(){
        ResponseEntity responseEntity = bookController.deleteBooks(BookData.IDS);

        assertStatus200(responseEntity.getStatusCode());
    }

    //    check field response
    private void assertBook(Book actual){
        assertThat(actual.getId(), is(BookData.ID));
        assertThat(actual.getCategory(), is(BookData.CATEGORY));
        assertThat(actual.getCreateDate(), is(BookData.CREATEDATE));
        assertThat(actual.getDescription(), is(BookData.DESCRIPTION));
        assertThat(actual.getImage(), is(BookData.IMAGE));
        assertThat(actual.getTitle(),is(BookData.TITLE));
        assertThat(actual.getModifiedDate(), is(BookData.MODIFIEDDATE));
    }

    private void assertStatus200(HttpStatus actual){
        assertThat(actual, is(HttpStatus.OK));
    }

    private void assertBooks(Books actual){
        assertBook(actual.getListBook().get(0));
        assertThat(actual.getNumPage(), is(BookData.NUMPAGE));
    }
}
