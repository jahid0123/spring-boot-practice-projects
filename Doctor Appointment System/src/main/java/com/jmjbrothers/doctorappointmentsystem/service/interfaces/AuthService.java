package com.jmjbrothers.doctorappointmentsystem.service.interfaces;

import com.jmjbrothers.doctorappointmentsystem.dto.AuthRequest;
import com.jmjbrothers.doctorappointmentsystem.dto.AuthResponse;
import com.jmjbrothers.doctorappointmentsystem.dto.RegisterRequest;
import com.jmjbrothers.doctorappointmentsystem.model.User;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
}

