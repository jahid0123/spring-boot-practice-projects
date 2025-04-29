package com.jmjbrothers.task_management_system.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import com.jmjbrothers.task_management_system.enums.UserRole;

@Data
@RequiredArgsConstructor

public class SignupRequest {

    private String name;
    private String email;
    private String password;
    private UserRole userRole;


}
