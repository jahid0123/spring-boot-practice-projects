package com.jmjbrothers.task_management_system.dto;

import com.jmjbrothers.task_management_system.enums.UserRole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticationResponse {

    private String jwt;
    private Long userId;
    private UserRole userRole;
}
