package com.jmjbrothers.realestateportal.repository;

import com.jmjbrothers.realestateportal.model.PropertyPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyPostRepository extends JpaRepository<PropertyPost, Long> {

    List<PropertyPost> findAllByUserId(Long userId);
}