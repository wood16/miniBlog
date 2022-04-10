package com.bookshop.poc.service.mock;

import com.bookshop.poc.service.entity.BookEntity;
import com.bookshop.poc.service.exception.ErrorMessage;
import com.miniblog.poc.api.model.Book;
import com.miniblog.poc.api.model.BookReq;
import com.miniblog.poc.api.model.Books;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookData {
    public static final Long ID = Long.valueOf(10);
    public static final String TITLE = "HTML, CSS, and JavaScript All in One";
    public static final String IMAGE = "https://cssdeck.com/blog/wp-content/uploads/2015/03/must-read-html-css-books1.jpg";
    public static final String CATEGORY = "music";
    public static final String DESCRIPTION = "A solid understanding of HTML, CSS, and JavaScript -- and how they work together -- is vital to any kind of contemporary web development.  And these core technologies are only growing in importance as contemporary web development moves away from a reliance on proprietary technologies like Flash for rich interface and mobile applications.Teach Yourself HTML, CSS, and JavaScript All in One combines these three fundamental web development technologies into one clearly written, carefully organized, step-by-step tutorial that expertly guides the beginner through these three interconnected technologies.By the end of this book the reader will understand how these technologies work, and more importantly, how they work together to create modern web pages and sites. After completing the book\\'s lessons the reader will be able to confidently create basic but professional-looking interactive web sites.Coverage of the HTML5, CSS3, and ECMAScript 6 standards is thoughtfully integrated into every chapter of the book, mentioning new features or tools within context of the task or topic at hand. Every day, more and more people want to learn some HTML and CSS. Joining the professional web designers and programmers are new audiences who need to know a little bit of code at work (update a content management system or e-commerce store) and those who want to make their personal blogs more attractive. Many books teaching HTML and CSS are dry and only written for those who want to become programmers, which is why this book takes an entirely new approach.";
    public static final String CREATEDATE = "01-03-2022";
    public static final String MODIFIEDDATE = "01-03-2022";
    public static final List<Long> IDS = Arrays.asList(1L,2L,3L);
    public static final Integer NUMPAGE = 10;

    public static final String REASON_NOT_FOUND = "Entity Not Found";
    public static final String BAD_REQUEST_EXCEPTION = "Bad Request Exception";
    public static final String REASON_INTERNAL_ERROR = "Internal Error";


    public static Book mockBook(){
        Book book = new Book();
        book.setId(ID);
        book.setCategory(CATEGORY);
        book.setDescription(DESCRIPTION);
        book.setImage(IMAGE);
        book.setTitle(TITLE);
        book.setCreateDate(CREATEDATE);
        book.setModifiedDate(MODIFIEDDATE);
        return book;
    }

    public static Books mockBooks(){
        Books books = new Books();
        books.setNumPage(NUMPAGE);
        List<Book> list = new ArrayList<>();
        list.add(mockBook());
        books.setListBook(list);
        return books;
    }

    public static BookReq mockBookReq(){
        BookReq bookReq = new BookReq();
        bookReq.setCategory(CATEGORY);
        bookReq.setDescription(DESCRIPTION);
        bookReq.setImage(IMAGE);
        bookReq.setTitle(TITLE);
        bookReq.setCreateDate(CREATEDATE);
        bookReq.setModifiedDate(MODIFIEDDATE);
        return bookReq;
    }
    public static BookEntity mockBookEntity(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(ID);
        bookEntity.setCategory(CATEGORY);
        bookEntity.setDescription(DESCRIPTION);
        bookEntity.setImage(IMAGE);
        bookEntity.setTitle(TITLE);
        bookEntity.setCreateDate(CREATEDATE);
        bookEntity.setModifiedDate(MODIFIEDDATE);
        return bookEntity;
    }

    public static List<BookEntity> mockBookEntities(){
        return Arrays.asList(mockBookEntity());
    }
}
