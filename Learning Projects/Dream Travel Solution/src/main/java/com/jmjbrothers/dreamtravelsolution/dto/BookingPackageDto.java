package com.jmjbrothers.dreamtravelsolution.dto;

import com.jmjbrothers.dreamtravelsolution.constants.Transportation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookingPackageDto {

    private Long userId;
    private Long packageId;

    @Enumerated(EnumType.STRING)
    private Transportation transport;
}
