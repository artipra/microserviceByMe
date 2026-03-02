package com.example.loans.controller;


import com.example.loans.constants.LoanConstants;
import com.example.loans.dto.LoanDto;
import com.example.loans.dto.LoansContactInfoDto;
import com.example.loans.dto.ResponseDto;
import com.example.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoansController {

    private static final Logger logger = LoggerFactory.getLogger(LoansController.class);

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private LoansContactInfoDto loansContactInfoDto;

    private ILoanService iLoanService;

    private LoansController(ILoanService iLoanService){
        this.iLoanService=iLoanService;
    }

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


    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo() {
        logger.debug("Invoked Loans contact-info API");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansContactInfoDto);
    }
}

