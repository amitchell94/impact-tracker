package com.codingnomads.impacttracker.presentation.api;

import com.codingnomads.impacttracker.logic.JWT.OurTokenService;
import com.codingnomads.impacttracker.logic.statistic.StatisticsService;
import com.codingnomads.impacttracker.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/impact")
public class ImpactApiController {
    private OurTokenService ourTokenService;

    private StatisticsService statisticsService;

    @Autowired
    public ImpactApiController(OurTokenService ourTokenService, StatisticsService statisticsService) {
        this.ourTokenService = ourTokenService;
        this.statisticsService = statisticsService;
    }

    @GetMapping("/total/{userId}")
    public Statistic getTotalImpactForUser(@RequestParam(name = "token") String tokenValue, @PathVariable Integer userId){
        ourTokenService.validateTokenByValue(tokenValue);
        return statisticsService.getImpactForTimePeriod(userId, 0);

    }

    @GetMapping("/week/{userId}")
    public Statistic getWeekImpactForUser(@RequestParam(name = "token") String tokenValue, @PathVariable Integer userId){
        ourTokenService.validateTokenByValue(tokenValue);
        return statisticsService.getImpactForTimePeriod(userId, 7);

    }

    @GetMapping("/month/{userId}")
    public Statistic getMonthImpactForUser(@RequestParam(name = "token") String tokenValue, @PathVariable Integer userId){
        ourTokenService.validateTokenByValue(tokenValue);
        return statisticsService.getImpactForTimePeriod(userId, 30);

    }

    @GetMapping("/year/{userId}")
    public Statistic getYearImpactForUser(@RequestParam(name = "token") String tokenValue, @PathVariable Integer userId){
        ourTokenService.validateTokenByValue(tokenValue);
        return statisticsService.getImpactForTimePeriod(userId, 365);

    }
}
