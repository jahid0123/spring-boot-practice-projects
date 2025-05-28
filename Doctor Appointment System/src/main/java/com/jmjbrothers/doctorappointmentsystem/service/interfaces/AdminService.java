package com.jmjbrothers.doctorappointmentsystem.service.interfaces;

import com.jmjbrothers.doctorappointmentsystem.constants.UserRole;
import com.jmjbrothers.doctorappointmentsystem.dto.UserSummaryDTO;

import java.util.List;
import java.util.Map;

public interface AdminService {
    List<UserSummaryDTO> getAllUsers();
    List<UserSummaryDTO> getUsersByRole(UserRole role);
    void deleteUser(Long userId);
    Map<UserRole, Long> getUserStats();
}