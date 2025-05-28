package com.jmjbrothers.doctorappointmentsystem.controller;

import com.jmjbrothers.doctorappointmentsystem.constants.UserRole;
import com.jmjbrothers.doctorappointmentsystem.dto.UserSummaryDTO;
import com.jmjbrothers.doctorappointmentsystem.service.interfaces.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserSummaryDTO>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/users/role/{role}")
    public ResponseEntity<List<UserSummaryDTO>> getUsersByRole(@PathVariable UserRole role) {
        return ResponseEntity.ok(adminService.getUsersByRole(role));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<UserRole, Long>> getUserStats() {
        return ResponseEntity.ok(adminService.getUserStats());
    }
}
