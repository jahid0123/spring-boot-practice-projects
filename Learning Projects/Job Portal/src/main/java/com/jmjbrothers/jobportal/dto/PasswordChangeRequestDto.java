package com.jmjbrothers.jobportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasswordChangeRequestDto {

    private Long id;
    private String currentPassword;
    private String newPassword;
}

