package com.codingnomads.impacttracker.presentation.api;

import com.codingnomads.impacttracker.logic.JWT.OurTokenService;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import com.codingnomads.impacttracker.model.Commitment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commitments")
public class CommitmentApiController {
    private CommitmentService commitmentService;

    private OurTokenService ourTokenService;

    @Autowired
    public CommitmentApiController(CommitmentService commitmentService, OurTokenService ourTokenService) {
        this.commitmentService = commitmentService;
        this.ourTokenService = ourTokenService;
    }

    @GetMapping("/{userId}")
    //postman request: http://localhost:8080/api/commitments/1
    public List<Commitment> allCommitments(@PathVariable Integer userId, @RequestParam(name = "token") String tokenValue) {
        ourTokenService.validateTokenByValue(tokenValue);
        return commitmentService.getCommitmentsFromUserId(userId);
    }

}
