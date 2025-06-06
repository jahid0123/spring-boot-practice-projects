package com.jmjbrothers.realestateportal.dto;

import com.jmjbrothers.realestateportal.constants.Type;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class GetPostedProperty {

    private Long id;
    private Type type;
    private String title;
    private String description;
    private Boolean isAvailable;
    private Integer propertyPrice;
    private LocalDate datePosted;
    private String address;
    private List<String> imageUrls;
}

