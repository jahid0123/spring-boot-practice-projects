package com.jmjbrothers.doctorsappointmentsystem.common;

import java.math.BigDecimal;

public record DashboardStatsDto(
        long totalAdmissions,
        long totalDischarges,
        BigDecimal totalRevenue,
        long totalBeds,
        long occupiedBeds
) {}

