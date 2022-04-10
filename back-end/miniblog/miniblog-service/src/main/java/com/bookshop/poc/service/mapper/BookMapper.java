package com.bookshop.poc.service.mapper;

import com.bookshop.poc.service.entity.BookEntity;
import com.bookshop.poc.service.repository.BookRepository;
import com.miniblog.poc.api.model.Book;
import com.miniblog.poc.api.model.BookReq;
import com.miniblog.poc.api.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {

    private final BookRepository bookRepository;

    @Autowired
    public BookMapper(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book mapBookResFromBookEntity(BookEntity from){
        Book to = new Book();
        to.setId(from.getId());
        to.setCategory(from.getCategory());
        to.setDescription(from.getDescription());
        to.setImage(from.getImage());
        to.setTitle(from.getTitle());
        to.setCreateDate(from.getCreateDate());
        to.setModifiedDate(from.getModifiedDate());
        return to;
    }
    public BookEntity mapBookEntityFromBookReq(Long id, BookReq from){
        //  update
        BookEntity to = bookRepository.getOne(id);
        to.setCategory(from.getCategory());
        to.setDescription(from.getDescription());
        to.setImage(from.getImage());
        to.setTitle(from.getTitle());

        LocalDate date = LocalDate.now();
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        to.setModifiedDate(dtf.format(date));

        return to;
    }
    public BookEntity mapBookEntityFromBookReq(BookReq from){
        //  create
        BookEntity to = new BookEntity();
        to.setCategory(from.getCategory());
        to.setDescription(from.getDescription());
        to.setImage(from.getImage());
        to.setTitle(from.getTitle());

        LocalDate date = LocalDate.now();
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        to.setCreateDate(dtf.format(date));

        return to;
    }
    public Books mapBookListFromBookEntities(List<BookEntity> from, int numPage){
        Books to = new Books();
        List<Book> list = new ArrayList<>();
        from.stream().forEach(entity -> {
            list.add(mapBookResFromBookEntity(entity));
        });
        to.setListBook(list);
        to.setNumPage(numPage);
        return to;
    }
}
