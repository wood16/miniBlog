package com.bookshop.poc.service.validator;

import com.bookshop.poc.service.exception.BadRequestException;
import com.bookshop.poc.service.exception.EntityNotFoundException;
import com.bookshop.poc.service.mock.BookData;
import com.bookshop.poc.service.repository.BookRepository;
import com.miniblog.poc.api.model.BookReq;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookValidatorTest {
    @InjectMocks
    BookValidator bookValidator;

    @Mock
    BookRepository bookRepository;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void validateAddBookTest(){
        bookValidator.validateAddBook(BookData.mockBookReq());
    }

    @Test
    public void validateUpdateBookTest(){
        when(bookRepository.existsById(BookData.ID)).thenReturn(true);

        bookValidator.validateUpdateBook(BookData.ID, BookData.mockBookReq());
    }
    @Test
    public void validateAddBookShowExceptionWhenRequiredFieldIsNull() {
        expected.expect(BadRequestException.class);
        expected.expectMessage("Book category is requested");

        BookReq bookReq = BookData.mockBookReq();
        bookReq.setCategory("");

        bookValidator.validateAddBook(bookReq);
    }

    @Test
    public void validateBookNotExist(){
        expected.expect(EntityNotFoundException.class);
        expected.expectMessage("Book does not exist");

        bookValidator.validateBookExist(10000L);
    }

    @Test
    public void validateDeleteBookEmptyList(){
        List<Long> ids = new ArrayList<>();
        bookValidator.validateDeleteBook(ids);
    }

    @Test
    public void validateDeleteBookIdNotExist(){
        expected.expect(EntityNotFoundException.class);
        expected.expectMessage("Book does not exist");

        bookValidator.validateDeleteBook(BookData.IDS);
    }


}


