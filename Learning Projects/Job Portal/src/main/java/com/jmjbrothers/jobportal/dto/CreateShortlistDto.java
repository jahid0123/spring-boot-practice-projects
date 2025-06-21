package com.jmjbrothers.jobportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
@Data
@RequiredArgsConstructor
public class CreateShortlistDto {

    private Long jobApplyId;
    private Long companyId;
    private String interviewDate;
}
