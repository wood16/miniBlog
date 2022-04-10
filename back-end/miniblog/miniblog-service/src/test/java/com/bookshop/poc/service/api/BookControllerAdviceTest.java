package com.bookshop.poc.service.api;

import com.bookshop.poc.service.exception.BadRequestException;
import com.bookshop.poc.service.exception.EntityNotFoundException;
import com.bookshop.poc.service.exception.ErrorMessage;
import com.bookshop.poc.service.mock.BookData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerAdviceTest {

    @InjectMocks
    BookControllerAdvice bookControllerAdvice;

    @Test
    public void handleEntityNotFoundExceptionTest(){
        EntityNotFoundException exception = new EntityNotFoundException("Entity Not Found");

        ResponseEntity<ErrorMessage> responseEntity = bookControllerAdvice.handleEntityNotFoundException(exception);

        assertThat(responseEntity.getBody().getCode(), is(Integer.toString(HttpStatus.NOT_FOUND.value())));
        assertThat(responseEntity.getBody().getReasonCode(), is(BookData.REASON_NOT_FOUND));
        assertThat(responseEntity.getBody().getDescription(), is(exception.getMessage()));

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void handleBadRequestExceptionTest(){
        BadRequestException exception = new BadRequestException("Bad Request Exception");

        ResponseEntity<ErrorMessage> responseEntity = bookControllerAdvice.handleBadRequestException(exception);

        assertThat(responseEntity.getBody().getCode(), is(Integer.toString(HttpStatus.BAD_REQUEST.value())));
        assertThat(responseEntity.getBody().getReasonCode(), is(BookData.BAD_REQUEST_EXCEPTION));
        assertThat(responseEntity.getBody().getDescription(), is(exception.getMessage()));

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void handleInternalErrorExceptionTest(){
        Exception exception = new Exception("Internal Error");

        ResponseEntity<ErrorMessage> responseEntity = bookControllerAdvice.handleInternalErrorException(exception);

        assertThat(responseEntity.getBody().getCode(), is(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value())));
        assertThat(responseEntity.getBody().getReasonCode(), is(BookData.REASON_INTERNAL_ERROR));
        assertThat(responseEntity.getBody().getDescription(), is(exception.getMessage()));

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));

    }

}
