package com.example.accounts.service.client;

import com.example.accounts.dto.LoansDto;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoanFeignClient {
    @GetMapping(value = "/api/fetch", consumes="application/json")
    public ResponseEntity<LoansDto> fetchLoanDetail(@RequestParam String mobileNumber);

}
