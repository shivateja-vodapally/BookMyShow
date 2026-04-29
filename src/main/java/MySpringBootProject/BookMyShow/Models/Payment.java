package MySpringBootProject.BookMyShow.Models;

import MySpringBootProject.BookMyShow.Models.Constants.BaseModel;
import MySpringBootProject.BookMyShow.Models.Constants.PaymentMethod;
import MySpringBootProject.BookMyShow.Models.Constants.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
    private LocalDateTime paymentTime;
    private double amount;
    private String referenceId;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @ManyToOne
    private Ticket ticket;
}

//TODO : Create the models for multi-transaction payment

/*
    Multi-Transaction payment
        Transaction class
        @OneToMany
        List<Transaction> inside payment class

    Ticket Payment
    1      M
    1      1
 */
