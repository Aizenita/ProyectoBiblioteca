package org.example.libraryapp.Views;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books_with_copy_status")
public class books_with_copy_status {
    @Id
    private Integer book_id;
    private String title;
    private Integer author_id;
    private String ISBN;
    private String genre;
    private Integer publication_year;
    private String copy_status;
    private Integer available_copies;

}
