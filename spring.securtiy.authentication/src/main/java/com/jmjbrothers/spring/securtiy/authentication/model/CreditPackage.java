package com.jmjbrothers.spring.securtiy.authentication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "credit_packages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "credit_amount", nullable = false)
    private Long creditAmount;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;


}
