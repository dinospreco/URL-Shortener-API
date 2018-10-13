package com.infobit.urlshortener.dao;

import com.infobit.urlshortener.entities.Url;
import com.infobit.urlshortener.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url getUrlByShortUrl(String shortUrl);
    List<Url> getAllByUser(User user);
}
