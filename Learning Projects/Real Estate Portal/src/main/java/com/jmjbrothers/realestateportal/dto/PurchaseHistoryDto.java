package com.jmjbrothers.realestateportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class PurchaseHistoryDto {

    private String packageName;
    private Integer postNumber;
    private BigDecimal packagePrice;
    private LocalDateTime datePurchased;



}