package org.example.libraryapp.logica;

import javax.persistence.*;

@Entity
@Table(name = "book_copies")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer copy_id;

    private String status;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}

