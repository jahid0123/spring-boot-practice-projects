package com.jmjbrothers.doctorsappointmentsystem.dto;

import com.jmjbrothers.doctorsappointmentsystem.constants.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@RequiredArgsConstructor
public class AppointmentResponseDto {

    private Long doctorId;
    private String doctorName;
    private String qualification;
    private String patientName;
    private LocalDate patientDob;
    private String patientGender;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Status appointmentStatus;
}
