package com.codingnomads.impacttracker.presentation;

import com.codingnomads.impacttracker.logic.Statistic;
import com.codingnomads.impacttracker.logic.StatisticsService;
import com.codingnomads.impacttracker.logic.commitment.Commitment;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class CommitmentController {

    @Autowired
    private CommitmentService commitmentService;

    @Autowired
    private StatisticsService statisticsService;

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

    //add variable for username once security is added
    @GetMapping("/commitments")
    public ModelAndView allCommitments(ModelAndView modelAndView){
        modelAndView.addObject("commitmentlist", commitmentService.getCommitmentsFromUserId(1));
        modelAndView.setViewName("/commitments");
        return modelAndView;
    }

    @GetMapping("/updatecommitment/{id}")
    public String goToUpdateCommitmentForm(@PathVariable(name = "id") Integer id, Model model){
        Commitment commitment = commitmentService.getCommitmentById(id);
        model.addAttribute("commitment", commitment);
        return "/updatecommitment";
    }


    @PostMapping("/updatecommitment/{id}")
    public String submitUpdatedCommitment(@PathVariable(name = "id") Integer id, @ModelAttribute Commitment commitment){
        //is setting the userId for the commitment (using their signin) needed first?
        commitmentService.updateCommitmentById(id, commitment);
        return "redirect:/commitments";
    }

    @GetMapping("/deletecommitment")
    public RedirectView deleteCommitment(@RequestParam(name = "id") Integer id){
        commitmentService.deleteCommitmentById(id);
        return new RedirectView("/commitments");
    }
}
