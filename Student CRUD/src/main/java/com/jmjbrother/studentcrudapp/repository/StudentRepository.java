package com.jmjbrother.studentcrudapp.repository;

import com.jmjbrother.studentcrudapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
