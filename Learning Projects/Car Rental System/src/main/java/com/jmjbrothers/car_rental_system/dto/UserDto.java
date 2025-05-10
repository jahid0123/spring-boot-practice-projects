package com.jmjbrothers.car_rental_system.dto;

import com.jmjbrothers.car_rental_system.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private UserRole userRole;
}
