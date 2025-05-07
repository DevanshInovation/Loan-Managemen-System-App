package com.app.loan_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.loan_management_system.entity.Loan;
import com.app.loan_management_system.service.LoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/loans")
@SecurityRequirement(name = "bearerAuth")
public class LoanController {
    @Autowired
    private LoanService loanService;
    
//  1.  Apply For Loan
    @Operation(summary = "Apply for a loan",
    		description = "Customer applies for a new loan", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/apply/{customerId}")
    public Loan applyLoan(@PathVariable Long customerId, @RequestBody Loan loan) {
        return loanService.applyLoan(customerId, loan);
    }
    
//  2.  Approve Load By Loan Id
    @Operation(summary = "Approve loan", 
    		description = "Admin approves the loan with given ID", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/approve/{loanId}")
    public Loan approveLoan(@PathVariable Long loanId) {
        return loanService.approveLoan(loanId);
    }

//  3.  Get full loan history for a customer (same as customer loans endpoint)
    @Operation(summary = "Get loan", 
    		description = "Get loan with given ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/customer/{customerId}")
    public List<Loan> getLoans(@PathVariable Long customerId) {
        return loanService.getLoansByCustomer(customerId);
    }
    
 // 4. Get all loans by status (PENDING, APPROVED, REPAID, OVERDUE)
    @Operation(summary = "Get loan by status", 
    		description = "Get loan with given status", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/status/{status}")
    public List<Loan> getLoansByStatus(@PathVariable String status) {
        return loanService.getLoansByStatus(status);
    }

    // 5. Get all overdue loans
    @Operation(summary = "Get Overdue loans", 
    		description = "Get overdue loans ", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/overdue")
    public List<Loan> getOverdueLoans() {
        return loanService.getOverdueLoans();
    }



    // 7. Report of all pending loans
    @Operation(summary = "Get all pending loans", 
    		description = "Get Report of all pending loans", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/pending")
    public List<Loan> getPendingLoans() {
        return loanService.getLoansByStatus("PENDING");
    }
}