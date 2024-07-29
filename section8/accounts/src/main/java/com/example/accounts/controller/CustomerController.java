package com.example.accounts.controller;

import com.example.accounts.dto.CustomerDetailsDto;
import com.example.accounts.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CustomerController {

    private ICustomerService customerservice;

    @GetMapping("/fetchcustomerdetails")
    public ResponseEntity<CustomerDetailsDto> getCustomerAllDetails(@RequestParam String mobileNumber){
      CustomerDetailsDto customerDetailsDto = customerservice.fetchCustomerDetail(mobileNumber);
      return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }

}
