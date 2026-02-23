package com.example.accounts.service.clients;

import com.example.accounts.dtos.CardsDto;
import com.example.accounts.dtos.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoanFeignClient {
    @GetMapping("api/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam String mobileNumber);
}
