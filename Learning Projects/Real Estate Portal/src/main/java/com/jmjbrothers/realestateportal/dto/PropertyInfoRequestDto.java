package com.jmjbrothers.realestateportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PropertyInfoRequestDto {

    private Long userId;
    private Long propertyPostId;
}
