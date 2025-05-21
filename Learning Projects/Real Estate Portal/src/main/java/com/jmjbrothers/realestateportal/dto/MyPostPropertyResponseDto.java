package com.jmjbrothers.realestateportal.dto;

import com.jmjbrothers.realestateportal.constants.Type;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class MyPostPropertyResponseDto {
    private Long id;
    private Type type;
    private String title;
    private String description;
    private Boolean isAvailable;
    private Integer propertyPrice;
    private LocalDate datePosted;
    private String address;


}
