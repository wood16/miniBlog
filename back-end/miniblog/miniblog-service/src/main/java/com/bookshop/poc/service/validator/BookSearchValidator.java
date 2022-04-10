package com.bookshop.poc.service.validator;

import com.bookshop.poc.service.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class BookSearchValidator {
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 20;

    private static final String TERM_INPUT_INVALID_MSG =
            "Term input is invalid. Please in put max 20 normal characters";

    public void validateTermSearch(String term) {
        if (term.length() <= MAX_LENGTH) {
            return;
        }
        throw new BadRequestException(TERM_INPUT_INVALID_MSG);
    }
}
