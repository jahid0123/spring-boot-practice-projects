package com.jmjbrothers.realestateportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BuyPackageDto {

    private Long userId;
    private Long postPackageId;

}