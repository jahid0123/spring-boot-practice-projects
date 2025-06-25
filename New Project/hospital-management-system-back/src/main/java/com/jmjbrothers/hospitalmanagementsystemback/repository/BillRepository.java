package com.jmjbrothers.hospitalmanagementsystemback.repository;

import com.jmjbrothers.hospitalmanagementsystemback.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> { }
