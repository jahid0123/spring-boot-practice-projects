package com.jmjbrothers.doctorappointmentsystem.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
}

