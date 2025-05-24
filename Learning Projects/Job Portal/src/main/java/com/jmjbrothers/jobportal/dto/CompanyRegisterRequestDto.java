package com.jmjbrothers.jobportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CompanyRegisterRequestDto {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String business;
    private String address;
}
