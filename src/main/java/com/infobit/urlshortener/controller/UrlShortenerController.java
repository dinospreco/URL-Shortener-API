package com.infobit.urlshortener.controller;

import com.infobit.urlshortener.dto.ShortRequestDTO;
import com.infobit.urlshortener.dto.ShortResponseDTO;
import com.infobit.urlshortener.services.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("register")
public class UrlShortenerController {

    @Autowired
    private ShortenerService shortenerService;

    @PostMapping
    public ResponseEntity<ShortResponseDTO> shortUrl(@RequestBody ShortRequestDTO shortRequestDTO, HttpServletRequest request) {
        return shortenerService.shortUrl(shortRequestDTO, request);
    }

}
