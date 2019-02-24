package com.codingnomads.impacttracker.presentation.api;

import com.codingnomads.impacttracker.logic.JWT.AuthenticationService;
import com.codingnomads.impacttracker.logic.reduction.ReductionService;
import com.codingnomads.impacttracker.model.Reduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reductions")
public class ReductionApiController {
    private ReductionService reductionService;

    private AuthenticationService authenticationService;

    @Autowired
    public ReductionApiController(ReductionService reductionService, AuthenticationService authenticationService) {
        this.reductionService = reductionService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/")
    public List<Reduction> allReductions(@RequestParam(name = "token") String tokenValue) {
        authenticationService.validateToken(tokenValue);

        List<Reduction> reductions = reductionService.getAllReductions();
        return reductions;
    }
}
