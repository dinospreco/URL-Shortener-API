package com.infobit.urlshortener.contoller;

import com.infobit.urlshortener.UrlshortenerApplication;
import com.infobit.urlshortener.dao.AccountDAO;
import com.infobit.urlshortener.entities.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UrlshortenerApplication.class)
@Transactional
public class AccountControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AccountDAO accountDAO;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenAccountId_whenAccountIsRegistering_then201IsReceived() throws Exception{
        //Given
        String accountId = "accountId";

        //When
        mockMvc.perform(
                post("/account")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(accountId)
        )
                //Then
                .andExpect(status().isCreated());
    }
    @Test
    public void givenExistingAccountId_whenAccountIsRegistering_then409IsReceived() throws Exception{
        //Given
        String accountId = "accountId";
        accountDAO.save(new Account(accountId,"password"));

        //When
        mockMvc.perform(
                post("/account")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(accountId)
        )
                //Then
                .andExpect(status().isConflict());
    }

    @Test
    public void givenAccountId_whenAccountIsRegistering_thenSuccessResponseIsReceived() throws Exception {
        //Given
        String accountId = "accountId";

        //When
        mockMvc.perform(
                post("/account")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(accountId)
        )
                //Then
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.description", is("Your account is opened")))
                .andExpect(jsonPath("$.password", notNullValue()));
    }

    @Test
    public void givenExistingAccountId_whenAccountIsRegistering_thenFailureResponseIsReceived() throws Exception {
        //Given
        String accountId = "accountId";
        accountDAO.save(new Account(accountId,"password"));

        //When
        mockMvc.perform(
                post("/account")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(accountId)
        )
                //Then
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.description", is("AccountId already exists")))
                .andExpect(jsonPath("$.password", nullValue()));
    }
}
