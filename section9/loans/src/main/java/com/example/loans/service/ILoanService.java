package com.example.loans.service;


import com.example.loans.dto.LoanDetailsDto;
import com.example.loans.dto.LoanDto;

public interface ILoanService {

     void createLoan(String mobileNumber);
     LoanDto fetchDetails(String mobileNumber);
     boolean updateLoan(LoanDto loanDto);
     boolean deleteLoan(String mobileNumber);

}
