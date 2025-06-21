package com.jmjbrothers.jobportal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "faysal_packages")
public class Packages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Basic, Premium, Premium Plus
    private Integer jobPostLimit;
    private Integer applicantViewLimit;
    private Integer validityMonth;
    private Double price;

}
