package com.infobip.urlshortener.controller;

import com.infobip.urlshortener.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <h2>Statistic Controller</h2>
 *
 * <p>
 *     This is REST Controller.
 *     Handles requests for "/statistic"
 * </p>
 *
 * @see com.infobip.urlshortener.services.StatisticService
 */
@RestController
@RequestMapping("statistic")
public class StatisticController {

    /**
     * {@link com.infobip.urlshortener.services.StatisticService}
     */
    @Autowired
    private StatisticService statisticService;

    /**
     * Handles GET Request. Request is forwarded to {@link com.infobip.urlshortener.services.StatisticService}
     * @param accountId Part of the URL right after root URL and represents account id
     * @param request HttpServletRequest - Needed for service logic
     * @return ResponseEntity with configured HttpStatus and JSON object E.g. {"www.veryLongUrl.com/someMoreOfLongUrls/AndEvenMoreLongUrls" : 10} 10 is number of visits
     */
    @GetMapping("{accountId}")
    public ResponseEntity<Map> getStatistic(@PathVariable String accountId, HttpServletRequest request) {
        return statisticService.getStatistics(accountId, request);
    }


}
