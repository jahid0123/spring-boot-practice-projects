package com.jmjbrothers.task_management_system.dto;

import com.jmjbrothers.task_management_system.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponse {

    private Long id;
    private String name;
    private String email;
    private UserRole role;
}
