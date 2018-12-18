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
    public List<Commitment> allCommitments(@PathVariable Integer userId, @RequestParam(name = "token") String tokenValue) {
        ourTokenService.validateTokenByValue(tokenValue);
        return commitmentService.getCommitmentsFromUserId(userId);
    }

    @PostMapping("/addcommitment")
    public Commitment addCommitment(@RequestParam(name = "token") String tokenValue, @RequestBody Commitment commitment) {
        ourTokenService.validateTokenByValue(tokenValue);
        return commitmentService.save(commitment);
    }

    @PutMapping("/updatecommitment/{id}")
    public Commitment updateCommitmentById(@RequestParam(name = "token") String tokenValue, @PathVariable int id, @RequestBody Commitment commitment) {
        ourTokenService.validateTokenByValue(tokenValue);
        return commitmentService.updateCommitmentById(id, commitment);
    }

    @DeleteMapping("/deletecommitment/{id}")
    public void deleteCommitment(@RequestParam(name = "token") String tokenValue, @PathVariable int id) {
        ourTokenService.validateTokenByValue(tokenValue);
        commitmentService.deleteCommitmentById(id);
    }

}
