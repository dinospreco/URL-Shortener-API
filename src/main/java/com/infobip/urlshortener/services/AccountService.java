package com.infobip.urlshortener.services;

import com.infobip.urlshortener.dao.UserRepository;
import com.infobip.urlshortener.dto.AccountDTO;
import com.infobip.urlshortener.dto.RegistrationDTO;
import com.infobip.urlshortener.entities.User;
import com.infobip.urlshortener.utility.AlphanumericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <h2>Account Service</h2>
 * <p>
 *     This service class is responsible for all the logic behind Account Registration feature
 * </p>
 *
 * @see com.infobip.urlshortener.controller.AccountController
 */
@Service
public class AccountService {

    /**
     * {@link com.infobip.urlshortener.dao.UserRepository}
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * BCryptPasswordEncoder
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Gets {@link com.infobip.urlshortener.dto.AccountDTO} from {@link com.infobip.urlshortener.controller.AccountController}.
     * Checks if User already exists. If not, saves user to the database and returns response for controller
     * @param accountDTO
     * @return ResponseEntity with configured HttpStatus and JSON object E.g. {"success":true, "description":"Your account is opened", "password":"ABC12345"}
     */
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

    /**
     * Checks if user exists in database
     * @param username
     * @return True if exists, false if not
     */
    private boolean isAccountExisting(String username) {
        return userRepository.getUserByUsername(username) != null;
    }
}
