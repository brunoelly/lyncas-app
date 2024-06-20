package com.app.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class AccountsPayable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate dueDate;
    
    private LocalDate paymentDate;
    
    @Column(nullable = false)
    private BigDecimal valor;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private Status status;
}