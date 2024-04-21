package com.example.message.functions;

import com.example.message.dto.AccountMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

@Bean
public Function<AccountMsgDto, AccountMsgDto> email(){
       return  accountMsgDto -> {
         log.info("sending email with the deatils "+ accountMsgDto.toString());
         return accountMsgDto;
       };
    }

    @Bean
    public Function<AccountMsgDto, Long> sms(){
        return  accountMsgDto -> {
            log.info("sending sms with the deatils "+ accountMsgDto.toString());
            return accountMsgDto.accountNumber();
        };
    }
}
