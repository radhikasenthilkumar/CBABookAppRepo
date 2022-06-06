package com.dxc.cba.bookSystem.controller;

import com.dxc.cba.bookSystem.exception.CustomBookException;
import com.dxc.cba.bookSystem.exception.NoSuchBookExistsException;
import com.dxc.cba.bookSystem.model.Book;
import com.dxc.cba.bookSystem.repository.BookRepository;
import com.dxc.cba.bookSystem.service.BookService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * This class is responsible for handling all RESTFul API's calls.
 */
@RestController
@RequestMapping("/books")
public class BookController {
    Logger logger = LoggerFactory.logger(BookController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    /**
     * Get all books.
     *
     * @return List<Books>
     */
    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        logger.info("Get All Books");
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    /**
     * Get the book by id
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        //check if book exist in database
        logger.info("Get Book by id. Book id: " + id);
        Book book = bookService.getBookById(id);
        if (book != null) {
            logger.info("Book object" + book.toString());
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        } else {
            throw new NoSuchBookExistsException("No Such Book exists for the given Id!!");
        }
    }

    /**
     * Add a new book
     * @param book
     * @return
     */
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        logger.info("Save Book");
        logger.info("Book object :" + book.toString());
        if(book != null){
            return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
        }else{
            throw new CustomBookException("Book is not valid");
        }
    }

    /**
     * Get Filtered books based on the criteria passed
     * @param title
     * @param author
     * @param isbn
     * @return
     */
    @GetMapping("/filteredBooks")
    public ResponseEntity<List<Book>> getBooksByCriteria(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Long isbn) {
        logger.info("Filter Book based on title :" + title + "" +
                " and author : " + author + "and isbn : " +isbn);
        return new ResponseEntity<>(bookService.getBooksByCriteria(title, author,isbn), HttpStatus.OK);
    }

    /**
     * Method to update an existing book
     * @param id
     * @param bookRecord
     * @return
     */
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable("id") final Long id, @Valid @RequestBody final Book bookRecord) {
        logger.info(" Update Book of id : " + id);
        Book book = bookService.getBookById(id);
        if (book != null) {
            bookRecord.setId(id);
            return new ResponseEntity<>(bookService.updateBook(bookRecord), HttpStatus.OK);
        } else {
            throw new NoSuchBookExistsException("No Such Book exists for the given Id!!");
        }
    }

    /**
     * Delete a book based on it's id
     * @param id
     * @return
     */
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") long id) {
        //check if book exist in database
        logger.info("Delete book of id: " + id);
        Book book = bookService.getBookById(id);
        if (book != null) {
            bookService.deleteBookById(id);
            return new ResponseEntity<>("Deleted the book of id : " + id,HttpStatus.OK);
        } else {
            throw new NoSuchBookExistsException("No Such Book exists for the given Id!!");
        }
    }

    /**
     * Delete all books in the database
     * @return
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        logger.info("Delete All books");
        try {
            bookService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
