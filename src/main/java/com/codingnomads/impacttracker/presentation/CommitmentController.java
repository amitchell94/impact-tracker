package com.codingnomads.impacttracker.presentation;

import com.codingnomads.impacttracker.logic.Commitment;
import com.codingnomads.impacttracker.logic.CommitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@RestController
@RequestMapping("/")
public class CommitmentController {

    @Autowired
    CommitmentService commitmentService;

    @PostMapping("/add_commitment")
    public ModelAndView commitment(@ModelAttribute Commitment commitment, ModelAndView modelAndView) {
        commitment.setUserId(1);

        Commitment savedCommitment = commitmentService.save(commitment);
        modelAndView.setViewName("add_commitment_complete");
        modelAndView.addObject("savedCommitment", savedCommitment);

        return modelAndView;
    }
}
