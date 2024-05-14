package org.example.libraryapp.Views;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authors_book_count_view")
public class authors_book_count{
    @Id
    private Integer author_id;
    private String name;
    private Long book_count;


    // Getters and setters
}
