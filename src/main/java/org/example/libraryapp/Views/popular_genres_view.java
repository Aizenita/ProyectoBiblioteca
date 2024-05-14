package org.example.libraryapp.Views;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "popular_genres_view")
public class popular_genres_view {

    @Id
    private String genre;
    private Long book_count;


}
