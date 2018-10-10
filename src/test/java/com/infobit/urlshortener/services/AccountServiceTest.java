package com.infobit.urlshortener.services;

import com.infobit.urlshortener.UrlshortenerApplication;
import com.infobit.urlshortener.dao.AccountDAO;
import com.infobit.urlshortener.dto.RegistrationDTO;
import com.infobit.urlshortener.entities.Account;
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

    @Autowired
    private AccountDAO accountDAO;

    @Test
    public void givenLength_whenGeneratingPassword_thenRandomAlphaNumericStringIsReturned() {
        String generatedPassword = accountService.generateAlphaNumericPassword(8);
        //This regex checks  non-alphanumeric characters
        assertFalse(generatedPassword.matches("^.*[^a-zA-Z0-9].*$"));
    }

    @Test
    public void givenLength_whenGeneratingPassword_thenRandomStringEightCharactersLongIsReturned() {
        String generatedPassword = accountService.generateAlphaNumericPassword(8);
        assertEquals(8, generatedPassword.length());
    }

    @Test
    public void givenAccountId_whenCheckingIfAccountIdExists_thenFalseIsReturned() {
        assertFalse(accountService.isAccountExisting("accountId"));
    }

    @Test
    public void givenExistingAccountId_whenCheckingIfAccountIdExists_thenTrueIsReturned() {
        accountDAO.save(new Account("accountId","password"));
        assertTrue(accountService.isAccountExisting("accountId"));
    }

    @Test
    public void givenAccountId_whenRegisteringAccount_thenResponseEntitySuccessWithRegistrationDTOIsReturned() {
        ResponseEntity<RegistrationDTO> returnedRE = accountService.register("accountId");

        assertTrue(returnedRE.getBody().isSuccess());
        assertEquals("Your account is opened",returnedRE.getBody().getDescription());
        assertNotNull(returnedRE.getBody().getPassword());
    }

    @Test
    public void givenExistingAccountId_whenRegisteringAccount_thenResponseEntityFailureWithRegistrationDTOIsReturned() {
        accountDAO.save(new Account("accountId","password"));
        ResponseEntity<RegistrationDTO> returnedRE = accountService.register("accountId");

        assertFalse(returnedRE.getBody().isSuccess());
        assertEquals("AccountId already exists",returnedRE.getBody().getDescription());
        assertNull(returnedRE.getBody().getPassword());
    }
}
