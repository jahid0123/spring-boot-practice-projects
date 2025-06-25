package com.jmjbrothers.doctorsappointmentsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class EditPatientDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private LocalDate dob;
    private String address;
}
