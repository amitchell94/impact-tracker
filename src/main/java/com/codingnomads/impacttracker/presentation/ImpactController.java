package com.codingnomads.impacttracker.presentation;

import com.codingnomads.impacttracker.logic.statistic.StatisticsService;
import com.codingnomads.impacttracker.logic.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImpactController {

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    UserService userService;

    @GetMapping("/impacts")
    public ModelAndView myImpact(ModelAndView modelAndView) {
        modelAndView.setViewName("my_impact");
        modelAndView.addObject("totalImpact",
                statisticsService.getImpactForTimePeriod(userService.getCurrentUserId(), 0));
        return modelAndView;
    }

    @GetMapping("/impacts/week")
    public ModelAndView myImpactWeek(ModelAndView modelAndView) {
        modelAndView.setViewName("my_impact");
        modelAndView.addObject("totalImpact",
                statisticsService.getImpactForTimePeriod(userService.getCurrentUserId(), 7));
        return modelAndView;
    }

    @GetMapping("/impacts/month")
    public ModelAndView myImpactMonth(ModelAndView modelAndView) {
        modelAndView.setViewName("my_impact");
        modelAndView.addObject("totalImpact",
                statisticsService.getImpactForTimePeriod(userService.getCurrentUserId(),30));
        return modelAndView;
    }

    @GetMapping("/impacts/year")
    public ModelAndView myImpactYear(ModelAndView modelAndView) {
        modelAndView.setViewName("my_impact");
        modelAndView.addObject("totalImpact",
                statisticsService.getImpactForTimePeriod(userService.getCurrentUserId(),365));
        return modelAndView;
    }
}
