package com.example.loans.service.impl;

import com.example.loans.constants.LoanConstants;
import com.example.loans.dto.LoanDto;
import com.example.loans.entity.Loan;
import com.example.loans.exception.LoanAlreadyExistsException;
import com.example.loans.exception.ResourceNotFoundException;
import com.example.loans.mapper.LoanMapper;
import com.example.loans.repository.LoanRepository;
import com.example.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

     private LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> loans = loanRepository.findByMobileNumber(mobileNumber);
        if(loans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));

    }

    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }


    @Override
    public LoanDto fetchDetails(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
           () -> new ResourceNotFoundException("Loan","mobile number", mobileNumber));

        return LoanMapper.mapToLoansDto(loan, new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        boolean isUpdate = false;
        if(loanDto != null) {
            Loan loan = loanRepository.findByMobileNumber(loanDto.getMobileNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Loan", "mobile number", loanDto.getMobileNumber()));
            Loan updateLoan = LoanMapper.mapToLoans(loanDto, loan);
            loanRepository.save(updateLoan);
            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobile number", mobileNumber));
        loanRepository.deleteById(loan.getLoanId());
        return true;
    }
}
