package com.bookshop.poc.service.validator;

import com.bookshop.poc.service.exception.BadRequestException;
import com.bookshop.poc.service.mock.BookData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookSearchValidatorTest {

    @InjectMocks
    BookSearchValidator bookSearchValidator;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void validateTermSearchTest(){
        bookSearchValidator.validateTermSearch(BookData.CATEGORY);
    }

    @Test
    public void validateTermSearchShowExceptionWhenRequiredFieldIsFalse() {
        expected.expect(BadRequestException.class);
        expected.expectMessage("Term input is invalid. Please in put max 20 normal characters");
        bookSearchValidator.validateTermSearch("checkcheckcheckcheckcheck");
    }

}
