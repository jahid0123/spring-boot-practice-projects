package com.jmjbrothers.doctorappointmentsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@RequiredArgsConstructor
@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateIssued = LocalDate.now();
    private String diagnosis;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<Medicine> medicines = new ArrayList<>();

    @OneToOne
    private Appointment appointment;

    @ManyToOne
    private User doctor;

    @ManyToOne
    private User patient;

    // Getters and Setters
}
