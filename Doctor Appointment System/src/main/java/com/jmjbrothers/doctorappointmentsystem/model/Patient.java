package com.jmjbrothers.doctorappointmentsystem.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Entity
public class Patient extends User {
    private String phone;
    private String gender;
    private LocalDate dob;
    private String address;
}