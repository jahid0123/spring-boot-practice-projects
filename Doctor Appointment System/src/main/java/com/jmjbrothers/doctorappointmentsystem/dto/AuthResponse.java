package com.jmjbrothers.doctorappointmentsystem.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse {
    private String token;
    private String role;

    public AuthResponse(String token, String name) {
        this.token = token;
        this.role = name;
    }
}


