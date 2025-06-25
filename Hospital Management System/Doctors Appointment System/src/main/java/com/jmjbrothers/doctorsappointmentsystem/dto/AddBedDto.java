package com.jmjbrothers.doctorsappointmentsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBedDto {

    private String ward; // ICU, General, Private, etc.
    private String bedNumber;
    private Double feePerDay;

}
