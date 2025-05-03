package com.jmj.spring.security.app.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SignupRequest {

    private String username;
    private String password;
    private String name;
    private String address;

}
