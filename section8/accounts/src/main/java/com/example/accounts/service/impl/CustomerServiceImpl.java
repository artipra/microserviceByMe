package com.example.accounts.service.impl;

import com.example.accounts.dtos.*;
import com.example.accounts.entity.Account;
import com.example.accounts.entity.Customer;
import com.example.accounts.exception.ResourceNotFoundException;
import com.example.accounts.mapper.AccountMapper;
import com.example.accounts.mapper.CustomerMapper;
import com.example.accounts.repository.AccountRepository;
import com.example.accounts.repository.CustomerRepository;
import com.example.accounts.service.ICustomerService;
import com.example.accounts.service.clients.CardFeignClient;
import com.example.accounts.service.clients.LoanFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardFeignClient cardFeignClient;
    private LoanFeignClient loanFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountMapper.mapToAccountDto(accounts, new AccountDto()));
        ResponseEntity<CardsDto>  cardsDto = cardFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDto.getBody());
        ResponseEntity<LoanDto>  loanDto = loanFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loanDto.getBody());
         return  customerDetailsDto;
    }
}
