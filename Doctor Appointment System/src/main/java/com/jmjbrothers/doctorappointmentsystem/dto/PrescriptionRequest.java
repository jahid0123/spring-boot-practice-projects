package com.jmjbrothers.doctorappointmentsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrescriptionRequest {
    private Long appointmentId;
    private String diagnosis;
    private List<MedicineDTO> medicines;
}
