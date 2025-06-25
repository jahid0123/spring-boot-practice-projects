package com.jmjbrothers.doctorsappointmentsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmjbrothers.doctorsappointmentsystem.constants.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class PatientResponseDto {


    private Long id;

    private String name;

    private String email;

    private String phone;

    private String gender;

    private LocalDate dob;

    private String address;

}
