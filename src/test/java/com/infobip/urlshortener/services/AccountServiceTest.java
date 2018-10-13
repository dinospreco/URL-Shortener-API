package com.infobip.urlshortener.services;

import com.infobip.urlshortener.UrlshortenerApplication;
import com.infobip.urlshortener.dto.AccountDTO;
import com.infobip.urlshortener.dto.RegistrationDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static junit.framework.TestCase.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UrlshortenerApplication.class)
@Transactional
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    private ResponseEntity<RegistrationDTO> newAccountResponse;
    private ResponseEntity<RegistrationDTO> existingAccountResponse;

    @Before
    public void setUp()  {
        newAccountResponse = accountService.register(new AccountDTO("newAccountId"));
        accountService.register(new AccountDTO("existingAccountId"));
        existingAccountResponse = accountService.register(new AccountDTO("existingAccountId"));
    }

    @Test
    public void givenNewAccountId_whenRegisteringAccount_thenResponseEntitySuccessIsTrue() {
        assertTrue(newAccountResponse.getBody().isSuccess());
    }

    @Test
    public void givenNewAccountId_whenRegisteringAccount_thenResponseEntityDescriptionIsConfirmation() {
        assertEquals("Your account is opened", newAccountResponse.getBody().getDescription());
    }

    @Test
    public void givenNewAccountId_whenRegisteringAccount_thenResponseEntityPasswordIsNotNull() {
        assertNotNull(newAccountResponse.getBody().getPassword());
    }

    @Test
    public void givenNewAccountId_whenRegisteringAccount_thenResponseEntityHasAlphanumericPassword() {
        //Regex matches for non-alphanumeric characters and spaces
        assertFalse(newAccountResponse.getBody().getPassword().matches("^.*[^a-zA-Z0-9].*$"));
    }

    @Test
    public void givenNewAccountId_whenRegisteringAccount_thenResponseEntityHas8CharsLongPassword() {
        assertEquals(8, newAccountResponse.getBody().getPassword().length());
    }

    @Test
    public void givenExistingAccountId_whenRegisteringAccount_thenResponseEntitySuccessIsFalse() {
        assertFalse(existingAccountResponse.getBody().isSuccess());
    }

    @Test
    public void givenExistingAccountId_whenRegisteringAccount_thenResponseEntityDescriptionIsFailure() {
        assertEquals("AccountId already exists", existingAccountResponse.getBody().getDescription());
    }

    @Test
    public void givenExistingAccountId_whenRegisteringAccount_thenResponseEntityPasswordIsNull() {
        assertNull(existingAccountResponse.getBody().getPassword());
    }
}
