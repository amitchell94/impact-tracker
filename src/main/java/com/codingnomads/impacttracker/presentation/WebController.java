package com.codingnomads.impacttracker.presentation;

import com.codingnomads.impacttracker.logic.StatisticsService;
import com.codingnomads.impacttracker.logic.commitment.Commitment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        return modelAndView;
    }
    @GetMapping("/add_commitment")
    public ModelAndView addCommitment(ModelAndView modelAndView) {
        Commitment commitment = new Commitment();

        modelAndView.setViewName("add_commitment");
        modelAndView.addObject("commitment",commitment);
        return modelAndView;
    }
    @GetMapping("/my_impact")
    public ModelAndView myImpact(ModelAndView modelAndView) {
        modelAndView.setViewName("my_impact");
        modelAndView.addObject("totalImpact", statisticsService.getTotalImpact(1));
        return modelAndView;
    }
}



