package com.app.loan_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.loan_management_system.entity.Customer;
import com.app.loan_management_system.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/customers")
@SecurityRequirement(name = "bearerAuth")

@Tag(name = "Customer", description = "Operations related to customer management")
public class CustomerController {
	
    @Autowired
    private CustomerService customerService;
//  1.  Add Customer
    @Operation(
            summary = "Add a new customer",
            description = "Creates a new customer with name, email, contact number, and address."
    )
    @PostMapping
    public Customer add(@RequestBody Customer c) { 
    	return customerService.addCustomer(c); 
    	}
    
//  2. Update Customer
    @Operation(
            summary = "Update customer details",
            description = "Retrieves customer information by ID."
    )
    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer c) {
    	return customerService.updateCustomer(id, c); }
    
   
//  1.  View Customer
    @Operation(
            summary = "Get customer details",
            description = "Retrieves customer information by ID."
    )
    @GetMapping("/{id}")
    public Customer view(@PathVariable Long id) {
    	return customerService.getCustomer(id); 
    	}
}
