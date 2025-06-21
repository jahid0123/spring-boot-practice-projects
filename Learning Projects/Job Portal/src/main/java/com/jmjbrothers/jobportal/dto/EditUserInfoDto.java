package com.jmjbrothers.jobportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EditUserInfoDto {
    private Long userId;
    private String name;
    private String phone;
}
