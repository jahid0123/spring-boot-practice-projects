package com.jmjbrothers.jobportal.repository;

import com.jmjbrothers.jobportal.model.ShortListed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortListedRepository extends JpaRepository<ShortListed, Long> {

    List<ShortListed> findByCompany_Id(Long companyId);

    List<ShortListed> findByJobApply_Job_Id(Long jobId);

    boolean existsByJobApply_IdAndCompany_Id(Long jobApplyId, Long companyId);

    List<ShortListed> findByJobApply_JobSeeker_Id(Long seekerId);
}
