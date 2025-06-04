package com.jmjbrothers.doctorsappointmentsystem.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
//@Entity
//@Table(name = "bithy_medicine")
public class Medicine {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

//    @Column(name = "medicine_name", nullable = false)
    private String name;

//    @Column(name = "medicine_dosage", nullable = false)
    private String dosage;      // e.g., "500mg"

//    @Column(name = "medicine_frequency")
    private String frequency; // e.g., "2 times a day"

//    @Column(name = "medicine_duration", nullable = false)
    private String duration;    // e.g., "5 days"

//    @ManyToOne
//    @JoinColumn(name = "prescription_id", referencedColumnName = "id", nullable = false)
//    private Prescription prescription;

    // Getters and Setters
}