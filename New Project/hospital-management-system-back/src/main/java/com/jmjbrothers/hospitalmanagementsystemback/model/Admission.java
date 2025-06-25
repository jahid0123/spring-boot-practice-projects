package com.jmjbrothers.hospitalmanagementsystemback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "admission")
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate admissionDate;
    private LocalDate dischargeDate;

    @ManyToOne
    private Patient patient;

    @OneToOne
    private Bed bed;

    private boolean discharged;
}
