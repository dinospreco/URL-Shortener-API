package com.infobit.urlshortener.services;

import com.infobit.urlshortener.dao.UrlRepository;
import com.infobit.urlshortener.entities.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class RedirectService {

    @Autowired
    private UrlRepository urlRepository;

    public ResponseEntity<Object> redirect(String shortUrl) throws URISyntaxException {
        Url url = getRequestedShortUrl(shortUrl);

        if (url == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        incrementAndSaveVisits(url);

        return ResponseEntity.status(url.getRedirectType()).headers(setHttpHeaders(url)).build();
    }

    private HttpHeaders setHttpHeaders(Url url) throws URISyntaxException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI(url.getOriginalUrl()));
        return httpHeaders;
    }

    private Url getRequestedShortUrl(String shortUrl) {
        return urlRepository.getUrlByShortUrl(shortUrl);
    }

    private Url incrementAndSaveVisits(Url url) {
        url.setVisits(url.getVisits()+1);
        return urlRepository.save(url);
    }


}
