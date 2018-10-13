package com.infobit.urlshortener.services;

import com.infobit.urlshortener.dao.UrlRepository;
import com.infobit.urlshortener.dao.UserRepository;
import com.infobit.urlshortener.dto.ShortRequestDTO;
import com.infobit.urlshortener.dto.ShortResponseDTO;
import com.infobit.urlshortener.entities.Url;
import com.infobit.urlshortener.entities.User;

import com.infobit.urlshortener.utility.AlphanumericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URL;

@Service
public class ShortenerService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UserRepository userRepository;

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

    private User getLoggedUser(HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        return userRepository.getUserByUsername(username);
    }

    private String getBaseUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    }

    private String  generateUniqueShortUrl(int length) {
        String shortUrl = AlphanumericGenerator.generateAlphaNumericString(length);
        while (!isShortUrlUnique(shortUrl)) {
            shortUrl = AlphanumericGenerator.generateAlphaNumericString(length);
        }
        return shortUrl;
    }

    private boolean isShortUrlUnique(String shortUrl) {
        return urlRepository.getUrlByShortUrl(shortUrl) == null;
    }

    private boolean isUrlValid(String url) {
        try {
            URI uri = new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
