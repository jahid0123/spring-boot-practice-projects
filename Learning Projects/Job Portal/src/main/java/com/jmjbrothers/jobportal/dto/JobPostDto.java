package com.jmjbrothers.jobportal.dto;

import com.jmjbrothers.jobportal.model.Company;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JobPostDto {

    private Long companyId;
    private String jobTitle;
    private String jobDescription;
    private String jobRequirement;
    private String jobResponsibilities;
    private String compensationBenefit;
    private String workPlace;
    private String employmentStatus;
    private String jobLocation;
}
