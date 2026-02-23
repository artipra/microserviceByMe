package com.example.accounts.service;


import com.example.accounts.dtos.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
