package com.codingnomads.impacttracker.presentation.api;

import com.codingnomads.impacttracker.logic.JWT.AuthenticationService;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import com.codingnomads.impacttracker.model.Commitment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commitments")
public class CommitmentApiController {
    private CommitmentService commitmentService;


    private AuthenticationService authenticationService;

    @Autowired
    public CommitmentApiController(CommitmentService commitmentService, AuthenticationService authenticationService) {
        this.commitmentService = commitmentService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/")
    public List<Commitment> allCommitments(@RequestParam(name = "token") String tokenValue) {
        authenticationService.validateToken(tokenValue);
        int userIdFromToken = authenticationService.getUserIdFromToken(tokenValue);
        return commitmentService.getCommitmentsFromUserId(userIdFromToken);
    }
//normally i would not call the mappings
// POST /api/commitments/addcommitment,
// PUT /api/commitments/updatecommitment,
// DELETE /api/commitments/deletecommitment.
    // I would rather leave them as
// POST /api/commitments
// PUT  /api/commitments/{id}
// DELETE /api/commitments/{id}
    // its simpler to user and you can have the same path for different HTTP methods!
    @PostMapping("/addcommitment")
    public Commitment addCommitment(@RequestParam(name = "token") String tokenValue, @RequestBody Commitment commitment) {
        authenticationService.validateToken(tokenValue);
        return commitmentService.save(commitment);
    }

    @PutMapping("/updatecommitment/{id}")
    public Commitment updateCommitmentById(@RequestParam(name = "token") String tokenValue, @PathVariable int id, @RequestBody Commitment commitment) {
        authenticationService.validateToken(tokenValue);
        return commitmentService.updateCommitmentById(id, commitment);
    }

    @DeleteMapping("/deletecommitment/{id}")
    public ResponseEntity<Commitment> deleteCommitment(@RequestParam(name = "token") String tokenValue, @PathVariable int id) {
        authenticationService.validateToken(tokenValue);
        commitmentService.deleteCommitmentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
