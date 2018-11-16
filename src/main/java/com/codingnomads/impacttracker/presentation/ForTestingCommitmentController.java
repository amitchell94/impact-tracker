package com.codingnomads.impacttracker.presentation;

import com.codingnomads.impacttracker.Logic.Commitment;
import com.codingnomads.impacttracker.Logic.CommitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

//COMMITMENT CONTROLLER FOR TESTING JDBC CONNECTION. DELETE LATER!!!!!

@RestController
@RequestMapping("/")
public class ForTestingCommitmentController {

    @Autowired
    CommitmentService commitmentService;

    @PostMapping("/commitment")
    public Commitment commitment() {
        Commitment commitment1 = new Commitment(2, 2, LocalDate.parse("2018-12-12"), LocalDate.parse("2018-12-13"), 12);

        return commitmentService.save(commitment1);
    }
}
