package com.bookshop.poc.service.api;

import com.bookshop.poc.service.service.BookService;
import com.bookshop.poc.service.validator.BookSearchValidator;
import com.bookshop.poc.service.validator.BookValidator;
import com.miniblog.poc.api.BooksApi;
import com.miniblog.poc.api.model.Book;
import com.miniblog.poc.api.model.BookReq;
import com.miniblog.poc.api.model.Books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path = "/v1",produces = "application/json")
public class BookController implements BooksApi {

    private final BookService bookService;

    private final BookValidator bookValidator;

    private final BookSearchValidator bookSearchValidator;

    @Autowired
    public BookController(BookService bookService, BookValidator bookValidator, BookSearchValidator bookSearchValidator) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
        this.bookSearchValidator = bookSearchValidator;
    }

    @Override
    public ResponseEntity<Books> getBooks(@RequestParam(value = "page",required = true) Integer page,
                                          @RequestParam(value = "pageSize",required = true) Integer pageSize,
                                          @RequestParam(value = "sort" , required = true) String sort,
                                          @RequestParam(value = "colName" , required = true) String colName,
                                          @RequestParam(value = "search" , required = false) String search) {
        bookSearchValidator.validateTermSearch(search);
        return new ResponseEntity<>(bookService.findAll(page, pageSize, sort, colName, search), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> getBookById(@PathVariable("bookId") Long id){
        bookValidator.validateBookExist(id);
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> saveBook(@RequestBody BookReq request){
        bookValidator.validateAddBook(request);
        return new ResponseEntity<>(bookService.save(request),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") Long bookId, @RequestBody BookReq request){
        bookValidator.validateUpdateBook(bookId, request);
        return new ResponseEntity<>(bookService.update(bookId, request),HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteBooks(@RequestParam(value = "ids", required = true) List<Long> bookIds){
        bookValidator.validateDeleteBook(bookIds);
        bookService.deletes(bookIds);
        return new ResponseEntity(HttpStatus.OK);
    }

}
