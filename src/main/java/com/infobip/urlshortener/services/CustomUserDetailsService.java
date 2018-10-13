package com.infobip.urlshortener.services;

import com.infobip.urlshortener.dao.UserRepository;
import com.infobip.urlshortener.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <h2>Custom User Details Service</h2>
 * <p>
 *     This class customizes UserDetailsService
 * </p>
 * <p>
 *     Spring Security Configuration.
 *     See: https://spring.io/projects/spring-security
 * </p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * {@link com.infobip.urlshortener.dao.UserRepository}
     */
    @Autowired
    private UserRepository userRepository;

    /**
     *  <p>
     *      Spring Security Configuration.
     *      See: https://spring.io/projects/spring-security
     *  </p>
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return user;
    }
}
