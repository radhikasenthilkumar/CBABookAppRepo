package com.dxc.cba.bookSystem.repository;

import com.dxc.cba.bookSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Native interface for CRUD operations extending JpaRepsoitory
 */
public interface BookRepository extends JpaRepository<Book,Long>, BookRepositoryCustom {

}


