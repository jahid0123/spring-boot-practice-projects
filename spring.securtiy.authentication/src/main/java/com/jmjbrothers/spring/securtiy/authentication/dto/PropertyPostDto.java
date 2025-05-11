package com.jmjbrothers.spring.securtiy.authentication.dto;

import com.jmjbrothers.spring.securtiy.authentication.constants.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class PropertyPostDto {

    private Long userID;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private String title;
    private String description;
    private String address;
    private String contactNumber;
    private String area;
    private LocalDate availableFrom;
    private Integer rentAmount;



}
