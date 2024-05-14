package org.example.libraryapp.logica;

import javax.persistence.*;
import java.util.Set;
import java.util.Date;
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer book_id;

    private String title;
    private String ISBN;
    private String genre;
    private Date publication_year;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book")
    private Set<Loan> loans;

    @OneToMany(mappedBy = "book")
    private Set<BookCopy> bookCopies;

    @OneToMany(mappedBy = "book")
    private Set<Transaction> transactions;
}
