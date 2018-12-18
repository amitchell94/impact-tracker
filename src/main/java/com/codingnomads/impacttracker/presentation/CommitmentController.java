package com.codingnomads.impacttracker.presentation;

import com.codingnomads.impacttracker.logic.commitment.CommitmentUtils;
import com.codingnomads.impacttracker.logic.reduction.ReductionService;
import com.codingnomads.impacttracker.logic.statistic.StatisticsService;
import com.codingnomads.impacttracker.logic.user.UserService;
import com.codingnomads.impacttracker.model.Commitment;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import com.codingnomads.impacttracker.model.CommitmentPresentation;
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
    private ReductionService reductionService;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommitmentUtils commitmentUtils;
    //add variable for username once security is added

    @GetMapping("/commitments")
    public ModelAndView allCommitments(ModelAndView modelAndView){
        modelAndView.addObject("commitmentlist",
                commitmentUtils.transformCommitmentListToCommitmentPresentationList(
                        commitmentService.getCommitmentsFromUserId(userService.getCurrentUserId())));
        modelAndView.setViewName("/commitments");
        return modelAndView;
    }

    @GetMapping("/addcommitment")
    public ModelAndView addCommitment(ModelAndView modelAndView) {
        Commitment commitment = new Commitment();

        modelAndView.setViewName("add_commitment");
        modelAndView.addObject("commitment",commitment);
        modelAndView.addObject("reductionList",reductionService.getAllReductions());
        return modelAndView;
    }

    @PostMapping("/addcommitment")
    public ModelAndView commitment(@ModelAttribute Commitment commitment, ModelAndView modelAndView) {
        commitment.setUserId(userService.getCurrentUserId());

        CommitmentPresentation savedCommitment =
                commitmentUtils.transformCommitmentToCommitmentPresentation(commitmentService.save(commitment));
        modelAndView.setViewName("add_commitment_complete");
        modelAndView.addObject("savedCommitment", savedCommitment);
        return modelAndView;
    }

    @GetMapping("/updatecommitment/{id}")
    public String goToUpdateCommitmentForm(@PathVariable(name = "id") Integer id, Model model){
        Commitment commitment = commitmentService.getCommitmentById(id);
        model.addAttribute("commitment", commitment);
        model.addAttribute("reductionList",reductionService.getAllReductions());
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
