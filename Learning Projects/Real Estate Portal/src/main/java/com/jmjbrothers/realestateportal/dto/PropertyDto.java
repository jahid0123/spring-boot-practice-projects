package com.jmjbrothers.realestateportal.dto;

import com.jmjbrothers.realestateportal.constants.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class PropertyDto {

    private Long userId;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    private String title;
    private String description;
    private String address;
    private Integer propertyPrice;
}