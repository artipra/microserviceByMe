package com.example.accounts.service.impl;

import com.example.accounts.dto.AccountsDto;
import com.example.accounts.dto.CustomerDetailsDto;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.dto.LoansDto;
import com.example.accounts.entity.Accounts;
import com.example.accounts.entity.Customer;
import com.example.accounts.exception.ResourceNotFoundException;
import com.example.accounts.mapper.AccountsMapper;
import com.example.accounts.mapper.CustomerMapper;
import com.example.accounts.repository.AccountsRepository;
import com.example.accounts.repository.CustomerRepository;
import com.example.accounts.service.ICustomerService;
import com.example.accounts.service.client.LoanFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {


     private AccountsRepository accountsRepository;
     private LoanFeignClient loanFeignClient;
     private CustomerRepository customerRepository;

    @Override
    public CustomerDetailsDto fetchCustomerDetail(String mobileNumber) {
       Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
               () -> new ResourceNotFoundException("customer","mobile no.",mobileNumber)
       );
       Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("customer","mobile no.",mobileNumber)
        );
       ResponseEntity<LoansDto> loanDto = loanFeignClient.fetchLoanDetail(mobileNumber);
       AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(account,new AccountsDto());

       CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
       customerDetailsDto.setAccountsDto(accountsDto);
       customerDetailsDto.setLoansDto(loanDto.getBody());
       return customerDetailsDto;
    }


}
