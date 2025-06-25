package com.jmjbrothers.hospitalmanagementsystemback.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String name;
    private String email;
    private String role; // ADMIN, DOCTOR, PATIENT
}
