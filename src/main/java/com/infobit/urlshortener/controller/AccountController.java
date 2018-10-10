package com.infobit.urlshortener.controller;

import com.infobit.urlshortener.dto.RegistrationDTO;
import com.infobit.urlshortener.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<RegistrationDTO> register(@RequestBody String accountId) {
        return accountService.register(accountId);
    }
}
