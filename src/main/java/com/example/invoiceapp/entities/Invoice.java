package com.example.invoiceapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String invoiceNumber;
    private Date date;
    private Double amount;
    private String customerName;
    private String address;
    private Long mobileNumber;
}
