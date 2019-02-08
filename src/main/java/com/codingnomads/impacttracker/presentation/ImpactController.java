package com.codingnomads.impacttracker.presentation;

import com.codingnomads.impacttracker.logic.statistic.StatisticsService;
import com.codingnomads.impacttracker.logic.user.UserService;
import com.codingnomads.impacttracker.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.codingnomads.impacttracker.logic.statistic.StatisticsService.convertChartRatesToChartPointArray;

@Controller
public class ImpactController {

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    UserService userService;

    @GetMapping("/impacts")
    public ModelAndView myImpact(ModelAndView modelAndView) {
        Statistic totalImpact =  statisticsService.getImpactForTimePeriod(userService.getCurrentUserId(), 0);
        modelAndView.setViewName("impacts");
        modelAndView.addObject("co2stats", totalImpact.getTonsOfCo2().getTotalImpact());
        modelAndView.addObject("waterstats", totalImpact.getGallonsOfWater().getTotalImpact());
        modelAndView.addObject("co2chartpoints",convertChartRatesToChartPointArray( totalImpact.getTonsOfCo2().getChartPoints()));
        modelAndView.addObject("waterchartpoints",convertChartRatesToChartPointArray(totalImpact.getGallonsOfWater().getChartPoints()));

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
