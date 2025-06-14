package com.jmjbrothers.realestateportal.dto;

import com.jmjbrothers.realestateportal.constants.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PropertyPostDto {

    private Long userID;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    private String title;
    private Integer bedroom;
    private Integer bathroom;
    private Integer size;
    private Integer garage;
    private Integer yearBuilt;
    private String description;
    private String address;
    private Integer price;

}