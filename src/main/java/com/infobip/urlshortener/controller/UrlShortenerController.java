package com.infobip.urlshortener.controller;

import com.infobip.urlshortener.dto.ShortRequestDTO;
import com.infobip.urlshortener.dto.ShortResponseDTO;
import com.infobip.urlshortener.services.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <h2>URL Shortener Controller</h2>
 *
 * <p>
 *     This is REST Controller.
 *     Handles requests for "/register"
 * </p>
 *
 * @see com.infobip.urlshortener.services.ShortenerService
 */
@RestController
@RequestMapping("register")
public class UrlShortenerController {

    /**
     * {@link com.infobip.urlshortener.services.ShortenerService}
     */
    @Autowired
    private ShortenerService shortenerService;

    /**
     * Handles GET Request. Request is forwarded to {@link com.infobip.urlshortener.services.ShortenerService}
     * @param shortRequestDTO Accepts JSON object E.g. { "url":"www.logUrlThatNeedsShortening" } or { "url":"www.logUrlThatNeedsShortening", "redirectType":302}
     * @param request
     * @return ResponseEntity with configured HttpStatus and {@link com.infobip.urlshortener.dto.ShortResponseDTO}
     */
    @PostMapping
    public ResponseEntity<ShortResponseDTO> shortUrl(@RequestBody ShortRequestDTO shortRequestDTO, HttpServletRequest request) {
        return shortenerService.shortUrl(shortRequestDTO, request);
    }

}
