package com.dxc.cba.bookSystem.service;

import com.dxc.cba.bookSystem.model.Book;
import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();
    public Book addBook(Book bookRecord);
    public Book updateBook(Book bookRecord);
    public void deleteBookById(Long id);
    public List<Book> getBooksByCriteria(String title, String author,Long isbn);
    public void deleteAll();

    public Book getBookById(long id);
}
