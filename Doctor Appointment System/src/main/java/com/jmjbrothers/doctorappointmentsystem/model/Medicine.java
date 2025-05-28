package com.jmjbrothers.doctorappointmentsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String dosage;      // e.g., "500mg"
    private String frequency;   // e.g., "2 times a day"
    private String duration;    // e.g., "5 days"

    @ManyToOne
    private Prescription prescription;

    // Getters and Setters
}
