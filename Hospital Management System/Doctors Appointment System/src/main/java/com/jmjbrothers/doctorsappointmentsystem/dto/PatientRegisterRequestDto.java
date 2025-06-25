package com.jmjbrothers.doctorsappointmentsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class PatientRegisterRequestDto {

    private String name;
    private String phone;
    private String gender;
    private String email;
    private LocalDate dob;
    private String address;
}
