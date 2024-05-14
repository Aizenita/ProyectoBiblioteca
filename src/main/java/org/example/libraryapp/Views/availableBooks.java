package org.example.libraryapp.Views;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "available_books_view")
public class availableBooks {

    @Id
    private Integer book_id;
    private String title;
    private Integer author_id;
    private String ISBN;
    private String genre;
    private Date publication_year;
    private String copy_status;
    private Integer available_copies;



}
