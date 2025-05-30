package com.jmjbrothers.realestateportal.repository;

import com.jmjbrothers.realestateportal.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}