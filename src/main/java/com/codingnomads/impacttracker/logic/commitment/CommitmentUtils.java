package com.codingnomads.impacttracker.logic.commitment;

import com.codingnomads.impacttracker.logic.reduction.ReductionService;
import com.codingnomads.impacttracker.model.Commitment;
import com.codingnomads.impacttracker.model.Reduction;
import com.codingnomads.impacttracker.model.CommitmentPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommitmentUtils {
    //utils is a bad name, its only used when you dont find something specific that this class does.
    //This class seems to be a transformer.
    // CommitmentToPresentationCommitmentTransformer
    // or CommitmentToPresentationTransformer
    // would be better names

    ReductionService reductionService;

    @Autowired
    public CommitmentUtils(ReductionService reductionService) {
        this.reductionService = reductionService;
    }

    //dont reinvent the wheel. StringUtils from Apache Commons has a method that does this.
    // https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html#capitalize-java.lang.String-
    //https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.8.1
    private static String capitaliseFirstLetter(String input) {
        if (input == null || input.length() < 1) {
            return input;
        }

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public CommitmentPresentation transformCommitmentToCommitmentPresentation(Commitment commitment) {
        CommitmentPresentation commitmentPresentation = new CommitmentPresentation();
        Reduction reduction = reductionService.getReductionById(commitment.getReductionId());

        commitmentPresentation.setId(commitment.getId());


        //text formatting could be extracted to other place. actually should be using Spring messages properties,
        // just in case you will want to internationalize it some day https://www.baeldung.com/spring-boot-internationalization.
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
}
