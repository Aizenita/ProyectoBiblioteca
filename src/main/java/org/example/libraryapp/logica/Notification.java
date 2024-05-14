package org.example.libraryapp.logica;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notification_id;

    private String message;
    private Date notification_date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}