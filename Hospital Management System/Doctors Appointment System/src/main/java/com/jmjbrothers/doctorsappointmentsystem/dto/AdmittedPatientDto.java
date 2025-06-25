package com.jmjbrothers.doctorsappointmentsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmittedPatientDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private LocalDate dob;
    private String address;
    private String doctorName;
    private String bedWard;
    private String bedNumber;
    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private double advanceAmount;
    private double dueAmount;
}
