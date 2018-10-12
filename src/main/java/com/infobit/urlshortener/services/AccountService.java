package com.infobit.urlshortener.services;

import com.infobit.urlshortener.dao.UserRepository;
import com.infobit.urlshortener.dto.AccountDTO;
import com.infobit.urlshortener.dto.RegistrationDTO;
import com.infobit.urlshortener.entities.User;
import com.infobit.urlshortener.utility.AlphanumericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<RegistrationDTO> register(AccountDTO accountDTO) {

        if (isAccountExisting(accountDTO.getAccountId())) {
            return new ResponseEntity<>(
                    new RegistrationDTO(false, "AccountId already exists", null),
                    HttpStatus.CONFLICT);
        }

        String generatedPassword = AlphanumericGenerator.generateAlphaNumericString8CharsLong();
        User newUser = new User(accountDTO.getAccountId(),passwordEncoder.encode(generatedPassword));
        User savedUser = userRepository.save(newUser);

        if (savedUser == null) {
            return new ResponseEntity<>(
                    new RegistrationDTO(false, "Error with database", null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(
                new RegistrationDTO(true, "Your account is opened", generatedPassword),
                HttpStatus.CREATED
        );
    }

    private boolean isAccountExisting(String username) {
        return userRepository.getUserByUsername(username) != null;
    }
}
