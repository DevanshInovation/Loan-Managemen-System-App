package com.app.loan_management_system.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String contactNumber;
    private String address;
    
    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Loan> loans;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Customer(Long id, String name, String email, String contactNumber, String address, List<Loan> loans) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contactNumber = contactNumber;
		this.address = address;
		this.loans = loans;
	}

	public Customer() {
		super();
	}
    
    
}
