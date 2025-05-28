package com.jmjbrothers.doctorappointmentsystem.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role;
}