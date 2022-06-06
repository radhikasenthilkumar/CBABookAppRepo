package com.dxc.cba.bookSystem.service;

import com.dxc.cba.bookSystem.model.Book;
import com.dxc.cba.bookSystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  This class provides some business functionalities
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        List<Book> book = new ArrayList<>();
        bookRepository.findAll().forEach(book::add);
        return book;
    }

    @Override
    public  Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getBooksByCriteria(String title, String author,Long isbn) {
        if(isbn == null && author == null && title == null){
            return getAllBooks();
        }else{
            return bookRepository.getBooksByCriteria(title, author,isbn);
        }
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public Book getBookById(long id) {
        Optional<Book> bookObj = bookRepository.findById(id);
        if (bookObj.isPresent()) {
            return bookObj.get();
        }
        return null;
    }

}


