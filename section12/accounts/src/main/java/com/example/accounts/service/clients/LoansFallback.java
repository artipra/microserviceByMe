package com.example.accounts.service.clients;

import com.example.accounts.dtos.LoanDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoanFeignClient{
    @Override
    public ResponseEntity<LoanDto> fetchLoanDetails(String mobileNumber) {
        return null;
    }
}
