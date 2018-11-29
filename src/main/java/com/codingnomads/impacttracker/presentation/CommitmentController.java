package com.codingnomads.impacttracker.presentation;

import com.codingnomads.impacttracker.logic.Statistic;
import com.codingnomads.impacttracker.logic.StatisticsService;
import com.codingnomads.impacttracker.logic.commitment.Commitment;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class CommitmentController {

    @Autowired
    CommitmentService commitmentService;

    @Autowired
    StatisticsService statisticsService;

    @PostMapping("/add_commitment")
    public ModelAndView commitment(@ModelAttribute Commitment commitment, ModelAndView modelAndView) {
        commitment.setUserId(1);

        Commitment savedCommitment = commitmentService.save(commitment);
        modelAndView.setViewName("add_commitment_complete");
        modelAndView.addObject("savedCommitment", savedCommitment);

        return modelAndView;
    }

    @GetMapping("get_statistic")
    public Statistic getStatistic () {
        return statisticsService.getTotalImpact(1);
    }
}
