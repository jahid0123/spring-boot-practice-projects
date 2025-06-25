package com.jmjbrothers.doctorsappointmentsystem.model;

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
@Table(name = "farzana_admission")
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate admissionDate = LocalDate.now();
    private LocalDate dischargeDate;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Bed bed;

    @ManyToOne
    private Doctor doctor; // 🔥 Added this line

    private boolean discharged = false;

    private double advanceAmount = 0.0;
    private double totalAmount = 0.0;
    private double dueAmount = 0.0;
}

