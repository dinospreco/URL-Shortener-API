package com.infobit.urlshortener.services;


import com.infobit.urlshortener.dao.AccountDAO;
import com.infobit.urlshortener.dto.RegistrationDTO;
import com.infobit.urlshortener.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public ResponseEntity<RegistrationDTO> register(String accountId) {
        if (isAccountExisting(accountId)) {
            return new ResponseEntity<>(
                    new RegistrationDTO(false, "AccountId already exists", null),
                    HttpStatus.CONFLICT);
        } else {
            Account registeredAccount = accountDAO.save(new Account(accountId, generateAlphaNumericPassword(8)));

            return new ResponseEntity<>(
                    new RegistrationDTO(true, "Your account is opened", registeredAccount.getPassword()),
                    HttpStatus.CREATED);
        }
    }

    public boolean isAccountExisting(String accountId) {
        if (accountDAO.getAccountByAccountId(accountId) == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public String generateAlphaNumericPassword(int lenght) {
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder stringBuilder = new StringBuilder(lenght);
        for (int i = 0 ; i < lenght ; i++) {
            stringBuilder.append(
                    characters.charAt(
                            rnd.nextInt(characters.length())
                    )
            );
        }
        return stringBuilder.toString();
    }


}
