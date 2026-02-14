package com.example.loans.controller;


import com.example.loans.constants.LoanConstants;
import com.example.loans.dto.LoanDto;
import com.example.loans.dto.ResponseDto;
import com.example.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class LoansController {

    private ILoanService iLoanService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoans(@RequestParam String mobileNumber){
         iLoanService.createLoan(mobileNumber);
         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam String mobileNumber){
          LoanDto loanDetail = iLoanService.fetchDetails(mobileNumber);
          return ResponseEntity
                  .status(HttpStatus.OK)
                  .body(loanDetail);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody LoanDto loanDto){
      boolean isUpdate = iLoanService.updateLoan(loanDto);
      if(isUpdate){
          return ResponseEntity
                  .status(HttpStatus.OK)
                  .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
      }
      else{
          return ResponseEntity
                  .status(HttpStatus.EXPECTATION_FAILED)
                  .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
      }
    }


    @DeleteMapping("delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam String mobileNumber){
        boolean isDelete = iLoanService.deleteLoan(mobileNumber);
        if(isDelete){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
        }
    }

}
