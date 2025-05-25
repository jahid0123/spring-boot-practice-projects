package com.meme.onlinebookportal.repository;

import com.meme.onlinebookportal.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByAuthorNid(Integer authorNid);
}
