package com.jmjbrothers.doctorsappointmentsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Data
@RequiredArgsConstructor
public class AddAdmissionDto {


    private Long bedId;
    private Long doctorId;
    private String address;
    private LocalDate dob;
    private String gender;
    private String phone;
    private String email;
    private String name;
    private double advanceAmount;
}
