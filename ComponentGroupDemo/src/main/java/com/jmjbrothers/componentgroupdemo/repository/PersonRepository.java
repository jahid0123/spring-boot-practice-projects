package com.jmjbrothers.componentgroupdemo.repository;

import com.jmjbrothers.componentgroupdemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
