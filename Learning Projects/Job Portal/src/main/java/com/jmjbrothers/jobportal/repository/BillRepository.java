package com.jmjbrothers.jobportal.repository;

import com.jmjbrothers.jobportal.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByCompany_Id(Long companyId);
}
