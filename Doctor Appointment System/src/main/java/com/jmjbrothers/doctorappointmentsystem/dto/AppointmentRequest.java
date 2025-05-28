package com.jmjbrothers.doctorappointmentsystem.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class AppointmentRequest {
    private Long doctorId;
    private LocalDate appointmentDate;
    private String reason;
}