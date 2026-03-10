package com.example.accounts.dtos;

import lombok.Data;

@Data
public class LoanDto {

    private String mobileNumber;
    private String loanType;
    private String loanNumber;
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;

}
