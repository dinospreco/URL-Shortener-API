package com.infobip.urlshortener.services;

import com.infobip.urlshortener.dao.UserRepository;
import com.infobip.urlshortener.dto.ShortRequestDTO;
import com.infobip.urlshortener.dto.ShortResponseDTO;
import com.infobip.urlshortener.entities.Url;
import com.infobip.urlshortener.dao.UrlRepository;
import com.infobip.urlshortener.entities.User;

import com.infobip.urlshortener.utility.AlphanumericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URL;

/**
 * <h2>Shortener Service</h2>
 * <p>
 *     This service class is responsible for all the logic behind Shortening Url feature
 * </p>
 *
 * @see com.infobip.urlshortener.controller.UrlShortenerController
 */
@Service
public class ShortenerService {

    /**
     * {@link com.infobip.urlshortener.dao.UrlRepository}
     */
    @Autowired
    private UrlRepository urlRepository;

    /**
     * {@link com.infobip.urlshortener.dao.UserRepository}
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Handles shortening request for {@link com.infobip.urlshortener.controller.UrlShortenerController}
     * @param shortRequestDTO
     * @param request
     * @return ResponseEntity with configured HttpStatus and {@link com.infobip.urlshortener.dto.ShortResponseDTO}
     */
    public ResponseEntity<ShortResponseDTO> shortUrl(ShortRequestDTO shortRequestDTO, HttpServletRequest request) {
        if (!isUrlValid(shortRequestDTO.getUrl())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (shortRequestDTO.getRedirectType() == 0) {
            shortRequestDTO.setRedirectType(301);
        }
        else if (shortRequestDTO.getRedirectType() < 301 || shortRequestDTO.getRedirectType() > 302) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Url url = new Url(
                shortRequestDTO.getUrl(),
                generateUniqueShortUrl(10),
                shortRequestDTO.getRedirectType(),
                getLoggedUser(request),
                0L
        );

        Url savedUrl = urlRepository.save(url);
        ShortResponseDTO shortResponseDTO = new ShortResponseDTO(getBaseUrl(request) + savedUrl.getShortUrl());

        return ResponseEntity.status(savedUrl.getRedirectType()).body(shortResponseDTO);
    }

    /**
     * Gets {@link com.infobip.urlshortener.entities.User} object of logged from database.
     * @param request
     * @return
     */
    private User getLoggedUser(HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        return userRepository.getUserByUsername(username);
    }

    /**
     * Generates full short url for response.
     * @param request
     * @return
     */
    private String getBaseUrl(HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() == 8080) {
            baseUrl = baseUrl + ":" + request.getServerPort() + "/";
        } else {
            baseUrl = baseUrl + "/";
        }
        return baseUrl;
    }

    /**
     * Generates unique alphanumeric string of given length
     * @param length
     * @return
     */
    private String  generateUniqueShortUrl(int length) {
        String shortUrl = AlphanumericGenerator.generateAlphaNumericString(length);
        while (!isShortUrlUnique(shortUrl)) {
            shortUrl = AlphanumericGenerator.generateAlphaNumericString(length);
        }
        return shortUrl;
    }

    /**
     * Checks if generated shortUrl exists already in database
     * @param shortUrl
     * @return true if shortUrl doesn't exists in database, false if exists
     */
    private boolean isShortUrlUnique(String shortUrl) {
        return urlRepository.getUrlByShortUrl(shortUrl) == null;
    }

    /**
     * Tries to create URI object from java.net from given url string
     * If it creates successfully, that means the given url string is valid url
     * @param url
     * @return true if java.net.URI is created. false if java.net.URI throws exception
     */
    private boolean isUrlValid(String url) {
        try {
            URI uri = new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
