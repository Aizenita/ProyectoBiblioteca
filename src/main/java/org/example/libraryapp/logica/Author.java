package org.example.libraryapp.logica;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer author_id;

    private String name;
    private String country;
    private String birth_date;

    @OneToMany(mappedBy = "author")
    private Set<Book> books;
}
