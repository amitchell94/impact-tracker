package com.codingnomads.impacttracker.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        ChartPoint[] chartPoints = new ChartPoint [2];
        chartPoints[0] = new ChartPoint(10,"04-01-2014");
        chartPoints[1] = new ChartPoint(20,"04-01-2018");



        modelAndView.addObject("chartpoints",chartPoints);
//        modelAndView.addObject("chartpoint2",new ChartPoint(20,"\"04/01/2018\""));
        modelAndView.setViewName("impacts");
        return modelAndView;
    }
}





