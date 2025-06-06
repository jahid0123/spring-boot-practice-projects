package com.jmjbrothers.realestateportal.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String phone;

}