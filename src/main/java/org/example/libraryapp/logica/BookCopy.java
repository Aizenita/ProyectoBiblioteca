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



    // Getters and Setters
    public Integer getCopy_id() {
        return copy_id;
    }

    public void setCopy_id(Integer copy_id) {
        this.copy_id = copy_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}

