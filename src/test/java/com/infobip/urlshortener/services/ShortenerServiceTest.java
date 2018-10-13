package com.infobip.urlshortener.services;

import com.infobip.urlshortener.UrlshortenerApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UrlshortenerApplication.class)
@Transactional
public class ShortenerServiceTest {



}
