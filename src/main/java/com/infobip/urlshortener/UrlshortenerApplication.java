package com.infobip.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h2>URL Shortener</h2>
 * <p>
 *     URL Shortener is HTTP service used for generating short URLs.
 *     It was part of the job application project at Infobip
 * </p>
 *
 * @author Dino Spreco
 * @version 1.0
 */
@SpringBootApplication
public class UrlshortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlshortenerApplication.class, args);
    }
}
