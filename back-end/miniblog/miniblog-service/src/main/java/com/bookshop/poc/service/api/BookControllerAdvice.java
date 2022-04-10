package com.bookshop.poc.service.api;

import com.bookshop.poc.service.exception.BadRequestException;
import com.bookshop.poc.service.exception.EntityNotFoundException;
import com.bookshop.poc.service.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class BookControllerAdvice extends ResponseEntityExceptionHandler {
    private static final String REASON_NOT_FOUND = "Entity Not Found";
    private static final String BAD_REQUEST_EXCEPTION = "Bad Request Exception";
    private static final String REASON_INTERNAL_ERROR = "Internal Error";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception) {
        ErrorMessage error = new ErrorMessage();

        error.setCode(Integer.toString(HttpStatus.NOT_FOUND.value()));
        error.setDescription(exception.getMessage());
        error.setReasonCode(REASON_NOT_FOUND);

        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException exception) {
        ErrorMessage error = new ErrorMessage();

        error.setCode(Integer.toString(HttpStatus.BAD_REQUEST.value()));
        error.setDescription(exception.getMessage());
        error.setReasonCode(BAD_REQUEST_EXCEPTION);

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleInternalErrorException(Exception exception) {
        ErrorMessage error = new ErrorMessage();

        error.setCode(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setDescription(exception.getMessage());
        error.setReasonCode(REASON_INTERNAL_ERROR);

        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
