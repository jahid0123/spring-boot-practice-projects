package com.jmjbrothers.dreamtravelsolution.dto;

import com.jmjbrothers.dreamtravelsolution.constants.Status;
import com.jmjbrothers.dreamtravelsolution.constants.Transportation;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateBookingDto {

    private Long bookingId;
    private Status status;
    private Transportation transport;
}
