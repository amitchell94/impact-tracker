package com.codingnomads.impacttracker.logic.commitment;

import com.codingnomads.impacttracker.logic.reduction.ReductionService;
import com.codingnomads.impacttracker.model.Commitment;
import com.codingnomads.impacttracker.model.CommitmentWithReduction;
import com.codingnomads.impacttracker.model.Reduction;
import com.codingnomads.impacttracker.model.CommitmentPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommitmentUtils {

    ReductionService reductionService;

    @Autowired
    public CommitmentUtils(ReductionService reductionService) {
        this.reductionService = reductionService;
    }

    private static String capitaliseFirstLetter(String input) {
        if (input == null || input.length() < 1) {
            return input;
        }

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public CommitmentPresentation transformCommitmentWithReductionToCommitmentPresentation(CommitmentWithReduction commitment) {
        CommitmentPresentation commitmentPresentation = new CommitmentPresentation();

        commitmentPresentation.setId(commitment.getId());


        if (!"days".equals(commitment.getReductionUnit())) {
            if (commitment.getAmountToReduceBy() == null || commitment.getAmountToReduceBy() == 0) {
                commitmentPresentation.setCommitment(capitaliseFirstLetter(commitment.getReduction()) + " by the average amount per day");
            } else {
                commitmentPresentation.setCommitment(capitaliseFirstLetter(commitment.getReduction()) + " by " +
                        commitment.getAmountToReduceBy().toString() + " " + commitment.getReductionUnit() + " per day");
            }
        } else {
            commitmentPresentation.setCommitment(capitaliseFirstLetter(commitment.getReduction()));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        commitmentPresentation.setStartDate(commitment.getStartDate().format(formatter));

        if (commitment.getEndDate() != null) {
            commitmentPresentation.setEndDate(commitment.getEndDate().format(formatter));
        } else {
            commitmentPresentation.setEndDate("Ongoing");
        }

        return commitmentPresentation;
    }

    public CommitmentPresentation transformCommitmentToCommitmentPresentation(Commitment commitment) {
        CommitmentPresentation commitmentPresentation = new CommitmentPresentation();
        Reduction reduction = reductionService.getReductionById(commitment.getReductionId());
        commitmentPresentation.setId(commitment.getId());


        if (commitment.getAmountToReduceBy() != null && commitment.getAmountToReduceBy() != 0) {
            commitmentPresentation.setCommitment(capitaliseFirstLetter(reduction.getReduction()) + " by " +
                    commitment.getAmountToReduceBy().toString() + " " + reduction.getUnit() + " per day");
        } else {
            commitmentPresentation.setCommitment(capitaliseFirstLetter(reduction.getReduction()));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        commitmentPresentation.setStartDate(commitment.getStartDate().format(formatter));

        if (commitment.getEndDate() != null) {
            commitmentPresentation.setEndDate(commitment.getEndDate().format(formatter));
        } else {
            commitmentPresentation.setEndDate("Ongoing");
        }

        return commitmentPresentation;
    }


    public List<CommitmentPresentation> transformCommitmentListToCommitmentPresentationList(List<Commitment> commitments) {
        List<CommitmentPresentation> commitmentPresentations = new ArrayList<>();

        for (Commitment commitment :
                commitments) {
            commitmentPresentations.add(transformCommitmentToCommitmentPresentation(commitment));
        }
        return commitmentPresentations;
    }

    public List<CommitmentPresentation> transformCommitmentWithReductionListToCommitmentPresentationList(List<CommitmentWithReduction> commitments) {
        List<CommitmentPresentation> commitmentPresentations = new ArrayList<>();

        for (CommitmentWithReduction commitment :
                commitments) {
            commitmentPresentations.add(transformCommitmentWithReductionToCommitmentPresentation(commitment));
        }
        return commitmentPresentations;
    }
}
