package com.jmjbrothers.jobportal.repository;

import com.jmjbrothers.jobportal.model.Job;
import com.jmjbrothers.jobportal.model.JobApply;
import com.jmjbrothers.jobportal.model.Seeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplyRepository extends JpaRepository<JobApply, Long> {

    boolean existsByJobAndJobSeeker(Job job, Seeker seeker);


    @Query("SELECT a FROM JobApply a WHERE a.job.id = :jobId")
    List<JobApply> findAllByJob_Id(@Param("jobId") Long jobId);


    List<JobApply> findAllByJobSeeker_Id(Long jobSeekerId);


    List<JobApply> findAllByJob_Company_Id(Long companyId);
}
