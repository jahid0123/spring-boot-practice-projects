package com.jmjbrothers.task_management_system.dto;

import com.jmjbrothers.task_management_system.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;


}
