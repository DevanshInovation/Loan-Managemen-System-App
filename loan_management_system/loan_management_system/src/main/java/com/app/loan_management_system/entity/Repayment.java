package com.app.loan_management_system.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Repayment {
    @Id @GeneratedValue
    private Long id;
    private LocalDate date;
    private double amount;

    @ManyToOne
    @JsonBackReference
    private Loan loan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public Repayment(Long id, LocalDate date, double amount, Loan loan) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.loan = loan;
	}

	public Repayment() {
		super();
	}
    
    
}