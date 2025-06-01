package com.jmjbrothers.doctorappointmentsystem.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse {
    private Long id;
    private String token;
    private String role;

    public AuthResponse(Long id, String token, String name) {
        this.token = token;
        this.role = name;
        this.id = id;
    }
}


