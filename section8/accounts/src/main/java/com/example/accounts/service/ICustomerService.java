package com.example.accounts.service;


import com.example.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {
    CustomerDetailsDto fetchCustomerDetail(String mobileNumber);
}
