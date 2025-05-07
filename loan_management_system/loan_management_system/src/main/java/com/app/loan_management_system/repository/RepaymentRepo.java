package com.app.loan_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.loan_management_system.entity.Repayment;

public interface RepaymentRepo extends JpaRepository<Repayment , Long>{
	

}
