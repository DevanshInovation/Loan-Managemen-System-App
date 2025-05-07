package com.app.loan_management_system.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "loans")
public class Loan {
    @Id @GeneratedValue
    private Long id;

    private double amount;
    private double interestRate;
    private int durationMonths;
    private String purpose;

    private String status; // PENDING, APPROVED, REJECTED, REPAID, OVERDUE

    private LocalDate startDate;
    private double remainingBalance;

    @ManyToOne 
    @JsonBackReference
    private Customer customer;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Repayment> repayments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getDurationMonths() {
		return durationMonths;
	}

	public void setDurationMonths(int durationMonths) {
		this.durationMonths = durationMonths;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public double getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance(double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Repayment> getRepayments() {
		return repayments;
	}

	public void setRepayments(List<Repayment> repayments) {
		this.repayments = repayments;
	}

	public Loan(Long id, double amount, double interestRate, int durationMonths, String purpose, String status,
			LocalDate startDate, double remainingBalance, Customer customer, List<Repayment> repayments) {
		super();
		this.id = id;
		this.amount = amount;
		this.interestRate = interestRate;
		this.durationMonths = durationMonths;
		this.purpose = purpose;
		this.status = status;
		this.startDate = startDate;
		this.remainingBalance = remainingBalance;
		this.customer = customer;
		this.repayments = repayments;
	}

	public Loan() {
		super();
	}
   
    
}
