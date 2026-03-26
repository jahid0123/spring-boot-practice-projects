package com.jmjbrothers.componentgroupdemo.repository;

import com.jmjbrothers.componentgroupdemo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
