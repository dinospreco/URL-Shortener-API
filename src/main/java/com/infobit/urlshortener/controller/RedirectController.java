package com.infobit.urlshortener.controller;

import com.infobit.urlshortener.services.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("")
public class RedirectController {

    @Autowired
    private RedirectService redirectService;

    @GetMapping("{shortUrl}")
    public ResponseEntity<Object> redirect(@PathVariable String shortUrl) throws URISyntaxException {
        return redirectService.redirect(shortUrl);
    }

}
