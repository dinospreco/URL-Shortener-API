package com.infobit.urlshortener.services;

import com.infobit.urlshortener.dao.UrlRepository;
import com.infobit.urlshortener.dao.UserRepository;
import com.infobit.urlshortener.entities.Url;
import com.infobit.urlshortener.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class StatisticService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Map> getStatistics(String accountId, HttpServletRequest request) {
        if (isRequestMatchedWithAuthenticatedUser(accountId, request)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(extractStatisticForUser(accountId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private Map<String, Long> extractStatisticForUser(String accountId) {
        User user = userRepository.getUserByUsername(accountId);
        Map<String, Long> statistic = new HashMap<>();
        List<Url> allUrls = urlRepository.getAllByUser(user);
        for (Url url : allUrls) {
            if (statistic.containsKey(url.getOriginalUrl())) {
                url.setVisits(url.getVisits() + statistic.get(url.getOriginalUrl()));
            }
            statistic.put(url.getOriginalUrl(), url.getVisits());
        }
        return statistic;
    }

    private boolean isRequestMatchedWithAuthenticatedUser(String accountId, HttpServletRequest request) {
        return accountId.equals(request.getUserPrincipal().getName());
    }

}
