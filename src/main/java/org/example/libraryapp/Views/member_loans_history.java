package org.example.libraryapp.Views;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "member_loans_history_view")
public class member_loans_history {

    @Id
    private Integer loan_id;
    private Integer member_id;
    private String member_name;
    private String member_surname;
    private Integer book_id;
    private String book_title;
    private Date loan_date;
    private Date return_date;

}
