package com.codingnomads.impacttracker.presentation;

import com.codingnomads.impacttracker.logic.Commitment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
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
}



