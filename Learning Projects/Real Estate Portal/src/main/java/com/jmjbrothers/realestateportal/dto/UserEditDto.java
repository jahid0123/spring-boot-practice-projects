package com.jmjbrothers.realestateportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserEditDto {

    private Long userId;

    private String name;
    private String phone;

}