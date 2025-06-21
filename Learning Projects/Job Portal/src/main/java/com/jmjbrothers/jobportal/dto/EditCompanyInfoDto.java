package com.jmjbrothers.jobportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EditCompanyInfoDto {

    private Long id;
    private String name;
    private String business;
    private String phone;
    private String address;
}
