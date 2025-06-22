package com.meme.onlinebookportal.repository;

import com.meme.onlinebookportal.model.OrderShipped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderShippedRepository extends JpaRepository<OrderShipped, Long> {
}
