package com.bookshop.poc.service.validator;

import com.bookshop.poc.service.exception.BadRequestException;
import com.bookshop.poc.service.exception.EntityNotFoundException;
import com.bookshop.poc.service.repository.BookRepository;
import com.miniblog.poc.api.model.BookReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class BookValidator {
    private static final String BOOK_DOES_NOT_EXIST = "Book does not exist";

    private static final String TITLE_REQUEST = "Book title is requested";

    private static final String DESCRIPTION_REQUEST = "Book description is requested";

    private static final String CATEGORY_REQUEST = "Book category is requested";

    private final BookRepository bookRepository;

    @Autowired
    public BookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void validateBookExist(Long id){
        if(bookRepository.existsById(id)){
            return;
        }
        throw new EntityNotFoundException(BOOK_DOES_NOT_EXIST);
    }

    public void validateAddBook(BookReq bookReq){
        checkRequiredField(bookReq.getTitle(), TITLE_REQUEST);
        checkRequiredField(bookReq.getCategory(), CATEGORY_REQUEST);
        checkRequiredField(bookReq.getDescription(), DESCRIPTION_REQUEST);
    }

    public void validateUpdateBook(Long id, BookReq bookReq){
        validateBookExist(id);
        checkRequiredField(bookReq.getTitle(), TITLE_REQUEST);
        checkRequiredField(bookReq.getCategory(), CATEGORY_REQUEST);
        checkRequiredField(bookReq.getDescription(), DESCRIPTION_REQUEST);
    }

    public void validateDeleteBook(List<Long> ids){
        if(!ids.isEmpty() && ids.size() != 0) {
            for (Long id : ids) {
                validateBookExist(id);
            }
        }
    }

    public void checkRequiredField(String value, String errorMsg){
        if(value == null || value.trim().isEmpty()){
            throw new BadRequestException(errorMsg);
        }
    }
}
