package com.jmjbrothers.doctorsappointmentsystem.service;

import com.jmjbrothers.doctorsappointmentsystem.common.DashboardStatsDto;
import com.jmjbrothers.doctorsappointmentsystem.repository.AdmissionRepository;
import com.jmjbrothers.doctorsappointmentsystem.repository.BedRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DashboardService {


    private final AdmissionRepository admissionRepository;


    private final BedRepository bedRepository;

    public DashboardService(AdmissionRepository admissionRepository, BedRepository bedRepository) {
        this.admissionRepository = admissionRepository;
        this.bedRepository = bedRepository;
    }

    // You might need to calculate total revenue based on payments/advance amounts

    public DashboardStatsDto getDashboardStats() {
        long totalAdmissions = admissionRepository.count();
        long totalDischarges = admissionRepository.countByDischargedTrue();
        long totalBeds = bedRepository.count();
        long occupiedBeds = bedRepository.countByOccupiedTrue();

        // Example: sum advance amounts or payments if stored in Admission or Payment entity
        BigDecimal totalRevenue = admissionRepository.sumAllAdvanceAmounts(); // you have to create this method

        return new DashboardStatsDto(totalAdmissions, totalDischarges, totalRevenue, totalBeds, occupiedBeds);
    }
}

