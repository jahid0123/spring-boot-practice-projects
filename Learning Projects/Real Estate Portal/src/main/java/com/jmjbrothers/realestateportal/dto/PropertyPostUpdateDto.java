package com.jmjbrothers.realestateportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PropertyPostUpdateDto {
    private Long postId;
    private Boolean isAvailable;

}