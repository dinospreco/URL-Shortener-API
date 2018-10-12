package com.infobit.urlshortener.services;

import com.infobit.urlshortener.dao.UserRepository;
import com.infobit.urlshortener.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return user;
    }
}
