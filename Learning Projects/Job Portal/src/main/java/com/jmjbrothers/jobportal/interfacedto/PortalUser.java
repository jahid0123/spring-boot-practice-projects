package com.jmjbrothers.jobportal.interfacedto;

import com.jmjbrothers.jobportal.constants.Role;

public interface PortalUser {
    Long getId();
    String getEmail();
    String getPassword();
    Role getRole();
    String getName();
}

