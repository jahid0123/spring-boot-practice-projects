package com.jmjbrothers.doctorappointmentsystem.model;

import com.jmjbrothers.doctorappointmentsystem.constants.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "bithy_appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Column(name = "appointment_time")
    private LocalTime appointmentTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 255)
    private AppointmentStatus status;

    private String symptoms;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;


    // getters & setters
}