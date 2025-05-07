package com.app.loan_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.loan_management_system.entity.Customer;
import com.app.loan_management_system.repository.CustomerRepo;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public Customer addCustomer(Customer customer) {
        return customerRepo.save(customer);
    }
    
    public Customer updateCustomer(Long id, Customer updated) {
        Customer existing = customerRepo.findById(id).orElseThrow();
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setContactNumber(updated.getContactNumber());
        existing.setAddress(updated.getAddress());
        return customerRepo.save(existing);
    }
    
    public Customer getCustomer(Long id) {
        return customerRepo.findById(id).orElseThrow();
    }
}