package com.dxc.cba.bookSystem.repository;

import com.dxc.cba.bookSystem.model.Book;
import java.util.List;

/**
 * This interface is for handling custom repository calls such as fetching books based on criteria apart
 * from basic CRUD operations.
 */
public interface BookRepositoryCustom {
    public List<Book> getBooksByCriteria(String title, String author,Long isbn);

}
