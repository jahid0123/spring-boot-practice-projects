package com.jmjbrothers.jobportal.commondto;

import com.jmjbrothers.jobportal.constants.Role;

public interface PortalUser {
    Long getId();
    String getEmail();
    String getPassword();
    Role getRole();
    String getName();
}

