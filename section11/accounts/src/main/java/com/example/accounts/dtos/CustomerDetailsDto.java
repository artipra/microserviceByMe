package com.example.accounts.dtos;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDetailsDto {

    private String name;
    private String email;
    private String mobileNumber;
    private AccountDto accountsDto;
    private CardsDto cardsDto;
    private LoanDto loansDto;
}
