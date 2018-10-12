package com.infobit.urlshortener.services;

import com.infobit.urlshortener.UrlshortenerApplication;
import com.infobit.urlshortener.dto.ShortRequestDTO;
import com.infobit.urlshortener.entities.User;
import com.sun.security.auth.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UrlshortenerApplication.class)
@Transactional
public class ShortenerServiceTest {

    @Autowired
    private ShortenerService shortenerService;

    private MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

    private ShortRequestDTO validShortRequestDTO;
    private ShortRequestDTO invalidShortRequestDTO;

    @Before
    public void setUp() {
        validShortRequestDTO = new ShortRequestDTO(
                "www.test.com",
                302
        );
        invalidShortRequestDTO = new ShortRequestDTO(
                " t e s t ",
                1000
        );
//        mockHttpServletRequest.setServerName("test");
//        mockHttpServletRequest.setServerPort(8080);
        mockHttpServletRequest.setUserPrincipal(new UserPrincipal("Dino2"));
    }

    @Test
    @WithMockUser(username = "Dino2")
    public void givenValidShortRequestAndAnonymousUser_whenGeneratingShortUrl_thenUnauthenticatedIsGiven() {
        assertEquals(400,shortenerService.shortUrl(invalidShortRequestDTO,mockHttpServletRequest).getStatusCodeValue());
    }
}
