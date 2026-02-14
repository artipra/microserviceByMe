package com.example.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoanDto {

    private String mobileNumber;
    private String loanType;
    private String loanNumber;
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;

}
