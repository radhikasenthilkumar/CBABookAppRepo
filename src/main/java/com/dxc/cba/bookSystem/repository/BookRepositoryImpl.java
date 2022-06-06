package com.dxc.cba.bookSystem.repository;

import com.dxc.cba.bookSystem.model.Book;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the implementation of the repository interfaces to handle all repo operations.
 */
@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> getBooksByCriteria(String title, String author,Long isbn) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = criteriaQuery.from(Book.class);
        List<Predicate> conditions = new ArrayList<>();

        if (title != null) {
            conditions.add(criteriaBuilder.like(book.get("title"), "%" + title + "%"));
        }
        if (author != null) {
            conditions.add(criteriaBuilder.like(book.get("author"), "%" + author + "%"));
        }
        if(isbn != null){
            conditions.add(criteriaBuilder.equal(book.get("isbn"), isbn));
        }
        criteriaQuery.where(conditions.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}