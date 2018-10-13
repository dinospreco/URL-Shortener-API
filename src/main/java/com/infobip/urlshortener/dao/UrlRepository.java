package com.infobip.urlshortener.dao;

import com.infobip.urlshortener.entities.Url;
import com.infobip.urlshortener.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h2>URL Repository</h2>
 *<p>
 *     JPA Repository Interface for {@link com.infobip.urlshortener.entities.Url}
 *</p>
 */
@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    /**
     * Retrieves {@link com.infobip.urlshortener.entities.Url} form database based on shorUrl param
     * @param shortUrl
     * @return
     */
    Url getUrlByShortUrl(String shortUrl);

    /**
     * Retreives list of {@link com.infobip.urlshortener.entities.Url} registered by {@link com.infobip.urlshortener.entities.User}
     * @param user
     * @return
     */
    List<Url> getAllByUser(User user);
}
