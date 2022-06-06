package com.dxc.cba.bookSystem.controller;

import com.dxc.cba.bookSystem.model.Book;
import com.dxc.cba.bookSystem.repository.BookRepository;
import com.dxc.cba.bookSystem.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService service;

    @MockBean
    private BookRepository repository;

    @Test
    public void testAddBook() throws Exception {

        Book book = new Book(1L,"Spring","John",1234567890123L,new Date());
        when(service.addBook(Mockito.any(Book.class))).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                        "/books/addBook").accept(MediaType.APPLICATION_JSON).content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andDo(print());
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> bookList = Arrays.asList(
                new Book(1L,"Spring","John",1234567890123L,new Date()),
                new Book(2L,"SpringBoot","Max",1234567890321L,new Date())
        );
        when(service.getAllBooks()).thenReturn(bookList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/books/allBooks");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testAddBookInvalidISBN() throws Exception {
        Book book = new Book(1L,"Spring","John",123456789012L,new Date());
        when(service.addBook(any())).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                        "/books/addBook").accept(MediaType.APPLICATION_JSON).content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book(1L,"Spring","John",1234567890123L,new Date());
        when(service.getBookById(1L)).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/books/book/{id}",1L);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testGetBookByCriteria() throws Exception {
        Book book = new Book(1L,"Spring","John",1234567890123L,new Date());
        List<Book> list= new ArrayList<Book>();
        list.add(book);
        when(service.getBooksByCriteria(book.getTitle(), book.getAuthor(), book.getIsbn())).thenReturn(list);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/books/filteredBooks");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = new Book(1L,"Spring","John",123456789012L,new Date());
        when(service.getBookById(1L)).thenReturn(book);
        doNothing().when(service).deleteBookById(book.getId());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/books/deleteBook/{id}",book.getId());
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testBookNotFoundById() throws Exception {
        Book book = new Book(1L,"Spring","John",123456789012L,new Date());
        when(service.getBookById(1L)).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/books/book/{id}",2L);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound()).andDo(print());
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book(1L,"Spring","JohnMax",1234567890123L,new Date());
        when(service.getBookById(1L)).thenReturn(book);
        when(service.updateBook(any())).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                        "/books/updateBook/{id}",1L).accept(MediaType.APPLICATION_JSON).content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
