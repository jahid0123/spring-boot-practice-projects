package com.jmjbrothers.realestateportal.repository;

import com.jmjbrothers.realestateportal.model.PropertyInfoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyInfoRequestRepository extends JpaRepository<PropertyInfoRequest, Long> {

    List<PropertyInfoRequest> findAllByUserId(Long userId);

}
