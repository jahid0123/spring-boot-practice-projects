package com.jmjbrothers.jobportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRegisterRequestDto {

    private String name;
    private String email;
    private String password;
    private String phone;

}
