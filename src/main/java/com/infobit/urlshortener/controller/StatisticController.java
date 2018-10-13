package com.infobit.urlshortener.controller;

import com.infobit.urlshortener.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("{accountId}")
    public ResponseEntity<Map> getStatistic(@PathVariable String accountId, HttpServletRequest request) {
        return statisticService.getStatistics(accountId, request);
    }


}
