package com.jmjbrothers.doctorappointmentsystem.service;

import com.jmjbrothers.doctorappointmentsystem.constants.UserRole;
import com.jmjbrothers.doctorappointmentsystem.dto.UserSummaryDTO;
import com.jmjbrothers.doctorappointmentsystem.repository.UserRepository;
import com.jmjbrothers.doctorappointmentsystem.service.interfaces.AdminService;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {


    private final UserRepository userRepo;

    public AdminServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserSummaryDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(UserSummaryDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserSummaryDTO> getUsersByRole(UserRole role) {
        return userRepo.findByRole(role)
                .stream()
                .map(UserSummaryDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public Map<UserRole, Long> getUserStats() {
        Map<UserRole, Long> stats = new EnumMap<>(UserRole.class);
        for (UserRole role : UserRole.values()) {
            stats.put(role, userRepo.countByRole(role));
        }
        return stats;
    }
}
