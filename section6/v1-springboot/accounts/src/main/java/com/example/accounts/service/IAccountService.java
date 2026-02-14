package com.example.accounts.service;

import com.example.accounts.dtos.CustomerDetailsDto;
import com.example.accounts.dtos.CustomerDto;
import org.springframework.stereotype.Service;


public interface IAccountService {

    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
