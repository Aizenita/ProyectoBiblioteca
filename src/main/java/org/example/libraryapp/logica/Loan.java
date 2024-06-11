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
    @ManyToOne
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;
    // Getters and Setters
    public Integer getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(Integer loan_id) {
        this.loan_id = loan_id;
    }

    public Date getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(Date loan_date) {
        this.loan_date = loan_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

}





