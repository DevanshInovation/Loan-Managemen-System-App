package com.app.loan_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.loan_management_system.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
