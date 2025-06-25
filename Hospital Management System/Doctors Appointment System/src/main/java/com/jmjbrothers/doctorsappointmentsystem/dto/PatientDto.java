package com.jmjbrothers.doctorsappointmentsystem.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class PatientDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String doctorName;
    private String bedWard;
    private String bedNumber;
    private Double dueAmount;
    private Double totalBill;  // For discharged patients
    private LocalDate dischargeDate; // For discharged patients
    private LocalDate admissionDate;
    private Double advanceAmount;

    // Getters and setters
    // Constructor(s)
}
