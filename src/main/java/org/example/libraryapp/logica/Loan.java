package org.example.libraryapp.logica;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loan_id;

    private Date loan_date;
    private Date return_date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
