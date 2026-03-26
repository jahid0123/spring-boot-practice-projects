package com.jmjbrothers.componentgroupdemo.repository;

import com.jmjbrothers.componentgroupdemo.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {

}
