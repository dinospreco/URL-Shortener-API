package com.infobip.urlshortener.controller;

import com.infobip.urlshortener.services.RedirectService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * <h2>Redirect Controller</h2>
 *
 * <p>
 *     This is REST Controller.
 *     It redirects user from short url to original long url
 * </p>
 *
 * @see com.infobip.urlshortener.services.RedirectService
 */
@RestController
@RequestMapping("")
public class RedirectController {

    /**
     * {@link com.infobip.urlshortener.services.RedirectService}
     */
    @Autowired
    private RedirectService redirectService;

    /**
     * Handles GET Request. Request is forwarded to {@link com.infobip.urlshortener.services.RedirectService}
     * @param shortUrl Part of the URL right after root URL and represents unique value for original URL
     * @return ResponseEntity with configured HttpStatus and HttpHeaders
     * @throws URISyntaxException
     */
    @GetMapping("{shortUrl}")
    public ResponseEntity<Object> redirect(@PathVariable String shortUrl) throws URISyntaxException {
        return redirectService.redirect(shortUrl);
    }

}
