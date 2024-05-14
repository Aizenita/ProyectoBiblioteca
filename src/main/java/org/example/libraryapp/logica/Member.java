package org.example.libraryapp.logica;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer member_id;

    private String name;
    private String surname;
    private String address;
    private String phone_number;
    private String email;

    @OneToMany(mappedBy = "member")
    private Set<Loan> loans;

    @OneToMany(mappedBy = "member")
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "member")
    private Set<Notification> notifications;
}