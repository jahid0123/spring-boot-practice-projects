package com.jmjbrothers.doctorsappointmentsystem.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EditBedDto {

    private Long id;
    private String ward; // ICU, General, Private, etc.
    private String bedNumber;
    private boolean occupied;
    private double feePerDay; // ✅ Add this field
}
