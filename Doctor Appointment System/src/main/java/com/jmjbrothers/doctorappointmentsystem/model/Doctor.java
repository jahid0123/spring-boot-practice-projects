package com.jmjbrothers.doctorappointmentsystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Doctor extends User {
    private String specialization;
    private String qualification;
    private int experience;
    private String hospitalName;
    private String phone;
    private String status; // "PENDING", "APPROVED", "REJECTED"
}