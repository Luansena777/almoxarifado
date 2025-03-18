package com.almoxarifado.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "withdrawals")
public class Withdrawals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    private Integer quantity;
    private String tipo;
    private LocalDateTime dateMovement;


}
