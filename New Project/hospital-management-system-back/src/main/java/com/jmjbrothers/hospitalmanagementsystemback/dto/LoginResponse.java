package com.jmjbrothers.hospitalmanagementsystemback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
}
