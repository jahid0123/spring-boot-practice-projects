package com.jmjbrothers.dreamtravelsolution.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;



@Data
@RequiredArgsConstructor
@Entity
@Table(name = "hemel_travel_package")
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package_name", nullable = false)
    private String packageName;

    @Column(name = "post_amount", nullable = false)
    private String packageDescription;

    @Column(name = "package_image")
    private String packageImage;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal packagePrice;
}
