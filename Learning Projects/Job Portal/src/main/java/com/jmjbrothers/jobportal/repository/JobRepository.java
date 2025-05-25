package com.jmjbrothers.jobportal.repository;

import com.jmjbrothers.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(value = "SELECT * FROM faysal_job WHERE company_id = :id", nativeQuery = true)
    List<Job> findAllByCompanyId(@Param("id") Long id);

}
