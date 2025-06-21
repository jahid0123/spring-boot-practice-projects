package com.jmjbrothers.jobportal.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "faysal_shortlisted")
public class ShortListed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_apply_id", nullable = false)
    private JobApply jobApply;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "interview_date", nullable = false)
    private LocalDateTime interviewDate;

    @Column(name = "shortlisted_at", nullable = false, updatable = false)
    private LocalDateTime shortlistedAt;

    @PrePersist
    public void prePersist() {
        if (shortlistedAt == null) {
            shortlistedAt = LocalDateTime.now();
        }
    }
}