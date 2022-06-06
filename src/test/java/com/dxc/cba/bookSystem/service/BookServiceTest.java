package com.dxc.cba.bookSystem.service;

import com.dxc.cba.bookSystem.exception.NoSuchBookExistsException;
import com.dxc.cba.bookSystem.model.Book;
import com.dxc.cba.bookSystem.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class BookServiceTest {
    @InjectMocks
    BookServiceImpl service;

    @Mock
    BookRepository repository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> actualBooks = new ArrayList<Book>();
        Book book1 = new Book(1L,"Spring","John",1234567890123L,new Date());
        Book book2= new Book(2L,"Java","Max",1234567890098L,new Date());
        actualBooks.add(book1);
        actualBooks.add(book2);
        when(repository.findAll()).thenReturn(actualBooks);
        List<Book> expected = service.getAllBooks();
        assertEquals(expected, actualBooks);
        assertEquals(2, expected.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testAddBook() {
        Book book = new Book(1L,"Spring","John",1234567890123L,new Date());
        when(repository.save(book)).thenReturn(book);
        Book savedBook=service.addBook(book);
        assertThat(savedBook).isNotNull();
        verify(repository).save(any(Book.class));
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book(1L,"Spring","John",1234567890123L,new Date());
        when(repository.save(book)).thenReturn(book);
        Book expected = service.updateBook(book);
        assertThat(expected).isNotNull();
        verify(repository).save(any(Book.class));
    }
    @Test
    public void testGetBookById() {
        Book book = new Book(1L,"Spring","John",1234567890123L,new Date());
        when(repository.findById(book.getId())).thenReturn(Optional.of(book));
        Book expected = service.getBookById(book.getId());
        assertThat(expected).isNotNull();
        assertThat(expected).isSameAs(book);
        verify(repository).findById(book.getId());
    }

    @Test
    public void testGetBooksByCriteria() {
        Book book = new Book(1L,"Spring","John",1234567890123L,new Date());
        List<Book> actual= new ArrayList<Book>();
        actual.add(book);
        when(repository.getBooksByCriteria(book.getTitle(), book.getAuthor(), book.getIsbn())).thenReturn(actual);
        List<Book> expected = service.getBooksByCriteria(book.getTitle(), book.getAuthor(), book.getIsbn());
        assertThat(expected).isNotNull();
        assertEquals(1, expected.size());
        verify(repository).getBooksByCriteria(book.getTitle(), book.getAuthor(), book.getIsbn());
    }

    @Test
    public void testGetBooksByCriteriaWithoutCriteriaValues() {
        Book book = new Book(1L,"Spring","John",1234567890123L,new Date());
        List<Book> actual= new ArrayList<Book>();
        actual.add(book);
        when(repository.getBooksByCriteria(null,null,null)).thenReturn(actual);
        List<Book> expected = service.getBooksByCriteria(null,null,null);
        assertThat(expected).isNotNull();
        assertEquals(0, expected.size());
    }

    @Test
    public void testDeleteBook() {
        Long bookId=1L;
        service.deleteBookById(bookId);
        verify(repository, times(1)).deleteById(bookId);
    }

    @Test
    void testGetByIdErrorWhenIdNotFound() {
        Book book = new Book(1L,"Spring","John",1234567890123L,new Date());
        when(repository.findById(2L)).thenThrow(new NoSuchBookExistsException());
        assertThrows(NoSuchBookExistsException.class,() -> {
            service.getBookById(2L);
        });
        verify(repository).findById(2L); 
    }
}
