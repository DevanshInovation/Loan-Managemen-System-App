package com.app.loan_management_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.loan_management_system.entity.Loan;
import com.app.loan_management_system.entity.Repayment;
import com.app.loan_management_system.repository.LoanRepo;
import com.app.loan_management_system.repository.RepaymentRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RepaymentService {
    @Autowired
    private RepaymentRepo repayRepo;
    
    @Autowired 
    private LoanRepo loanRepo;

    public Repayment makeRepayment(Long loanId, Repayment repayment) {
        Loan loan = loanRepo.findById(loanId).orElseThrow(() -> new EntityNotFoundException("Loan not found for id: " + loanId));
        repayment.setLoan(loan);
        
        // Check if the loan is already fully repaid
        if (loan.getRemainingBalance() <= 0) {
            throw new RuntimeException("This loan is already fully repaid");
        }

        // Check if the repayment amount is more than the remaining balance
        if (repayment.getAmount() > loan.getRemainingBalance()) {
            throw new RuntimeException("Repayment amount exceeds the remaining balance");
        }

        // Set loan reference in repayment entity
        repayment.setLoan(loan);

        // Update the loan's remaining balance after repayment
        loan.setRemainingBalance(loan.getRemainingBalance() - repayment.getAmount());

        // Check if the loan is fully repaid after the repayment
        if (loan.getRemainingBalance() <= 0) {
            loan.setStatus("REPAID");
        }

        // Save the updated loan and repayment details
        loanRepo.save(loan);
        return repayRepo.save(repayment);
    }

    public List<Loan> getOverdueLoans() {
        return loanRepo.findAll().stream()
            .filter(loan -> loan.getRemainingBalance() > 0 &&
                           loan.getStartDate().plusMonths(loan.getDurationMonths()).isBefore(LocalDate.now()))
            .peek(loan -> loan.setStatus("OVERDUE"))
            .collect(Collectors.toList());
    }
}
