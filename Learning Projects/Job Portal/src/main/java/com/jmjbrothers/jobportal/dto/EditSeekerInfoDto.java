package com.jmjbrothers.jobportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class EditSeekerInfoDto {

    private Long id;
    private String name;
    private String phone;
    private String education;
    private String jobExperience;
    private String designation;
    private String address;

}