package com.app.loan_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.loan_management_system.entity.Loan;

import com.app.loan_management_system.entity.Repayment;
import com.app.loan_management_system.service.RepaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/repayments")
public class RepaymentController {
    @Autowired
    private RepaymentService repaymentService;
//  1. Make Repyment
    @Operation(summary = "Repay loan", 
    		description = "Customer makes repayment for the loan", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/{loanId}")
    public Repayment repay(@PathVariable Long loanId, @RequestBody Repayment r) {
        return repaymentService.makeRepayment(loanId, r);
    }

//  2.  Apply For Loan
    @Operation(summary = "overdue loan", 
    		description = "Get all overdue loans", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/overdue")
    public List<Loan> overdueLoans() {
        return repaymentService.getOverdueLoans();
    }
}
