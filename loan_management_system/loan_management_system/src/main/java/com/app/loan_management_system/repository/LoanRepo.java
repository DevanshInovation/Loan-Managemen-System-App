package com.app.loan_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.loan_management_system.entity.Loan;

public interface LoanRepo extends JpaRepository<Loan, Long>{
	 List<Loan> findByCustomerId(Long customerId);
	 List<Loan> findByStatusIgnoreCase(String status);

}
