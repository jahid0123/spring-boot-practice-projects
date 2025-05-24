package com.jmjbrothers.jobportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SeekerRegisterRequestDto {

    private String name;
    private String email;
    private String password;
    private String phone;

}
