package com.codingnomads.impacttracker.presentation.api;

import com.codingnomads.impacttracker.logic.JWT.AuthenticationService;
import com.codingnomads.impacttracker.logic.statistic.StatisticsService;
import com.codingnomads.impacttracker.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/impact")
public class ImpactApiController {
    private AuthenticationService authenticationService;

    private StatisticsService statisticsService;

    @Autowired
    public ImpactApiController(StatisticsService statisticsService, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        this.statisticsService = statisticsService;
    }

 //probably i would create only one path and use a query parameter to select the value of the time period
    @GetMapping("/total")
    public Statistic getTotalImpactForUser(@RequestParam(name = "token") String tokenValue){
        authenticationService.validateToken(tokenValue);
        int userIdFromToken = authenticationService.getUserIdFromToken(tokenValue);
        return statisticsService.getImpactForTimePeriod(userIdFromToken, 0);

    }

    @GetMapping("/week")
    public Statistic getWeekImpactForUser(@RequestParam(name = "token") String tokenValue){
        authenticationService.validateToken(tokenValue);
        int userIdFromToken = authenticationService.getUserIdFromToken(tokenValue);
        return statisticsService.getImpactForTimePeriod(userIdFromToken, 7);

    }

    @GetMapping("/month")
    public Statistic getMonthImpactForUser(@RequestParam(name = "token") String tokenValue){
        authenticationService.validateToken(tokenValue);
        int userIdFromToken = authenticationService.getUserIdFromToken(tokenValue);
        return statisticsService.getImpactForTimePeriod(userIdFromToken, 30);

    }

    @GetMapping("/year")
    public Statistic getYearImpactForUser(@RequestParam(name = "token") String tokenValue){
        authenticationService.validateToken(tokenValue);
        int userIdFromToken = authenticationService.getUserIdFromToken(tokenValue);
        return statisticsService.getImpactForTimePeriod(userIdFromToken, 365);

    }
}
