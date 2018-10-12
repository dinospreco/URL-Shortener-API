package com.infobit.urlshortener.dao;

import com.infobit.urlshortener.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url getUrlByShortUrl(String shortUrl);
    Collection<Url> getAllByUserId(Long userId);
}
