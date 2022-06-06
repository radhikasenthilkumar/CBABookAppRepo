package com.dxc.cba.bookSystem.model;

import com.dxc.cba.bookSystem.validation.ISBNValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "books")
/**
 * This is the entity class of Book.
 */
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "title")
    @NotBlank(message = "Title cannot be empty or null")
    private String title;

    @Column(name = "author")
    @NotBlank(message = "Author name cannot be empty or null")
    private String author;

    @Column(name = "isbn")
    @NotNull(message = "ISBN number cannot be empty")
    @ISBNValidation(message = "ISBN should be 13 digit numeric value")
    private Long isbn;

    @Column(name = "publicationDate")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date publicationDate;

    public Book(long id, String title, String author, Long isbn, Date publicationDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn=" + isbn +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
