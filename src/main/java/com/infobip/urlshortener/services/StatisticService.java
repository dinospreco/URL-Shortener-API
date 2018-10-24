package com.infobip.urlshortener.services;

import com.infobip.urlshortener.dao.UrlRepository;
import com.infobip.urlshortener.dao.UserRepository;
import com.infobip.urlshortener.entities.Url;
import com.infobip.urlshortener.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <h2>Statistic Service</h2>
 * <p>
 *     This service class is responsible for all the logic behind statistics feature
 * </p>
 *
 * @see com.infobip.urlshortener.controller.StatisticController
 */
@Service
public class StatisticService {

    /**
     * {@link com.infobip.urlshortener.dao.UrlRepository}
     */
    @Autowired
    private UrlRepository urlRepository;

    /**
     * {@link com.infobip.urlshortener.dao.UserRepository}
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Handles statistic request from {@link com.infobip.urlshortener.controller.StatisticController}
     * @param accountId
     * @param request
     * @return ResponseEntity with configured HttpStatus and JSON object E.g. {"www.veryLongUrl.com/someMoreOfLongUrls/AndEvenMoreLongUrls" : 10} 10 is number of visits
     */
    public ResponseEntity<Map> getStatistics(String accountId, HttpServletRequest request) {
        if (isRequestMatchedWithAuthenticatedUser(accountId, request)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(extractStatisticForUser(accountId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Gets statistic as a Map for user
     * @param accountId
     * @return
     */
    private Map<String, Long> extractStatisticForUser(String accountId) {
        User user = userRepository.getUserByUsername(accountId);
        List<Url> urls = urlRepository.getAllByUser(user);
        return getOriginalUrlsAndNumberOfVisitsFromListOfUrlObjects(urls);
    }

    /**
     * Takes list of all {@link com.infobip.urlshortener.entities.Url} of user and generates Map
     * Key - Original url that is shorted
     * Value - Number of wisits
     * @param urls
     * @return
     */
    private Map<String, Long> getOriginalUrlsAndNumberOfVisitsFromListOfUrlObjects(List<Url> urls)  {
        Map<String, Long> statistic = new HashMap<>();
        for (Url url : urls) {
            if (statistic.containsKey(url.getOriginalUrl())) {
                url.setVisits(url.getVisits() + statistic.get(url.getOriginalUrl()));
            }
            statistic.put(url.getOriginalUrl(), url.getVisits());
        }
        return statistic;
    }

    /**
     * Check if accountId of logged user is matched with requested accountId
     * This is done so that one user cannot see statistics form other users
     * @param accountId AccountId that statistics are generated for
     * @param request HttpServletRequest object for extracting name of the logged user
     * @return true if logged users accountId is matched with requested, false if not
     */
    private boolean isRequestMatchedWithAuthenticatedUser(String accountId, HttpServletRequest request) {
        return accountId.equals(request.getUserPrincipal().getName());
    }

}
