package com.infobip.urlshortener.dao;

import com.infobip.urlshortener.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h2>URL Repository</h2>
 *<p>
 *     JPA Repository Interface for {@link com.infobip.urlshortener.entities.User}
 *</p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves User by username (accountId)
     * @param username
     * @return
     */
    User getUserByUsername(String username);
}
