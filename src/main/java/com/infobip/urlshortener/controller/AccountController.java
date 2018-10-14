package com.infobip.urlshortener.controller;

import com.infobip.urlshortener.dto.AccountDTO;
import com.infobip.urlshortener.dto.RegistrationDTO;
import com.infobip.urlshortener.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

/**
 * <h2>Account Controller</h2>
 *
 * <p>
 *     This is REST Controller mapped for /account.
 *     Uri "/account" is for registering account.
 * </p>
 *
 * @see com.infobip.urlshortener.services.AccountService
 */
@RestController
@RequestMapping("account")
public class AccountController {

    /**
     * {@link com.infobip.urlshortener.services.AccountService}
     */
    @Autowired
    private AccountService accountService;

    /**
     * This method handles POST request. Request is forwarded to the {@link com.infobip.urlshortener.services.AccountService}
     * @param accountDTO Accepts JSON object E.g. {"AccountId : "MyAccountId"}
     * @return ResponseEntity with configured HttpStatus and JSON object E.g. {"success":true, "description":"Your account is opened", "password":"ABC12345"}
     */
    @PostMapping
    public ResponseEntity<RegistrationDTO> register(@RequestBody AccountDTO accountDTO) {
        return accountService.register(accountDTO);
    }
}
