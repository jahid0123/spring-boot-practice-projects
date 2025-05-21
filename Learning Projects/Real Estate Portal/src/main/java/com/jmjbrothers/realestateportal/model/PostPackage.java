package com.jmjbrothers.realestateportal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "nahid_post_package")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package_name")
    private String name;

    @Column(name = "post_amount", nullable = false)
    private Integer numberOfPost;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal packagePrice;


}
