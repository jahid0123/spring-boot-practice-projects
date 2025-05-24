package com.jmjbrothers.jobportal.repository;

import com.jmjbrothers.jobportal.model.JobApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplyRepository extends JpaRepository<JobApply, Long> {
}
