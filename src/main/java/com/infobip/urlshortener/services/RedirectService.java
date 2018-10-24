package com.infobip.urlshortener.services;

import com.infobip.urlshortener.dao.UrlRepository;
import com.infobip.urlshortener.entities.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * <h2>Redirect Service</h2>
 * <p>
 *     This service class is responsible for all the logic behind Redirect feature
 * </p>
 *
 * @see com.infobip.urlshortener.controller.RedirectController
 */
@Service
public class RedirectService {

    /**
     * {@link com.infobip.urlshortener.dao.UrlRepository}
     */
    @Autowired
    private UrlRepository urlRepository;

    /**
     * Handles redirect request for {@link com.infobip.urlshortener.controller.RedirectController}
     * @param shortUrl
     * @return ResponseEntity with configured HttpStatus and HttpHeaders
     * @throws URISyntaxException
     */
    public ResponseEntity<Object> redirect(String shortUrl) throws URISyntaxException {
        Url url = getRequestedShortUrl(shortUrl);

        if (url == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        incrementAndSaveVisits(url);

        return ResponseEntity.status(url.getRedirectType()).headers(setHttpHeaders(url)).build();
    }

    /**
     * Sets Location for HttpHeaders that are needed in response of redirect request
     * @param url
     * @return
     * @throws URISyntaxException
     */
    private HttpHeaders setHttpHeaders(Url url) throws URISyntaxException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI(url.getOriginalUrl()));
        return httpHeaders;
    }

    /**
     * Gets {@link com.infobip.urlshortener.entities.Url} object from database
     * @param shortUrl
     * @return
     */
    private Url getRequestedShortUrl(String shortUrl) {
        return urlRepository.getUrlByShortUrl(shortUrl);
    }

    /**
     * Increments visits by one
     * @param url
     * @return
     */
    private Url incrementAndSaveVisits(Url url) {
        url.setVisits(url.getVisits()+1);
        return urlRepository.save(url);
    }


}
