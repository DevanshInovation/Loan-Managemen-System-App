package com.app.loan_management_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.loan_management_system.entity.Customer;
import com.app.loan_management_system.entity.Loan;
import com.app.loan_management_system.exception.LoanNotFoundException;
import com.app.loan_management_system.exception.ResourceNotFoundException;
import com.app.loan_management_system.repository.CustomerRepo;
import com.app.loan_management_system.repository.LoanRepo;

@Service
public class LoanService {
    @Autowired
    private LoanRepo loanRepo;
    @Autowired 
    private CustomerRepo customerRepo;

    public Loan applyLoan(Long customerId, Loan loan) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
       
        double principal = loan.getAmount();
        double rate = 8.0; // 8% fixed interest
        int months = loan.getDurationMonths();
        double time = months / 12.0;

        // Simple interest calculation
        double interest = (principal * rate * time) / 100;
        double totalRepayable = principal + interest;
        
        loan.setCustomer(customer);
        loan.setStatus("PENDING");
        loan.setRemainingBalance(totalRepayable);
        loan.setStartDate(LocalDate.now());
        return loanRepo.save(loan);
    }

    public Loan approveLoan(Long loanId) {
        Loan loan = loanRepo.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + loanId));
        if (loan.getAmount() < 100000) loan.setStatus("APPROVED");
        else loan.setStatus("REJECTED");
        return loanRepo.save(loan);
    }

    public List<Loan> getLoansByCustomer(Long customerId) {
        return loanRepo.findByCustomerId(customerId);
    }
    
    // 4. Get loans by status (e.g., PENDING, APPROVED, REPAID)
    public List<Loan> getLoansByStatus(String status) {
        return loanRepo.findByStatusIgnoreCase(status);
    }

    // 5. Get overdue loans
    public List<Loan> getOverdueLoans() {
        return loanRepo.findAll().stream()
            .filter(loan -> loan.getRemainingBalance() > 0 &&
                            loan.getStartDate().plusMonths(loan.getDurationMonths()).isBefore(LocalDate.now()))
            .peek(loan -> loan.setStatus("OVERDUE"))
            .collect(Collectors.toList());
    }
}