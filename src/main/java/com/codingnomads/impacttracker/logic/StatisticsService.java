package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.logic.commitment.Commitment;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import com.codingnomads.impacttracker.logic.impact.ImpactService;
import com.codingnomads.impacttracker.logic.impact.ImpactWithAverage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class StatisticsService {

    private ImpactService impactService;
    private CommitmentService commitmentService;



    @Autowired
    public StatisticsService(ImpactService impactService, CommitmentService commitmentService) {
        this.impactService = impactService;
        this.commitmentService = commitmentService;
    }

    public static Double roundToTwoDP(Double input) {
        if (input == null) return null;
        return (double) Math.round(input * 100d) / 100d;
    }

    public Statistic getTotalImpact(int userId) {

        List<Commitment> commitments = commitmentService.getCommitmentsFromUserId(userId);

        Statistic totalImpact = new Statistic();

        Map<Integer, List<ImpactWithAverage>> reductionImpactMap = new HashMap<>();

        for (Commitment commitmment : commitments) {

            int reductionId = commitmment.getReductionId();

            if (!reductionImpactMap.containsKey(reductionId)) {
                reductionImpactMap.put(reductionId,
                        impactService.getImpactsWithAveragesFromReductionId(reductionId));
            }

            Statistic impactPerDay = getImpactPerDay(reductionImpactMap.get(reductionId));

            long daysCommitted = DAYS.between(commitmment.getStartDate(), commitmment.getEndDate());

            totalImpact.setTonsOfCo2(totalImpact.getTonsOfCo2() + (impactPerDay.getTonsOfCo2() * daysCommitted));

            totalImpact.setGallonsOfWater(totalImpact.getGallonsOfWater()
                    + (impactPerDay.getGallonsOfWater() * daysCommitted));

        }

        totalImpact.setTonsOfCo2(roundToTwoDP(totalImpact.getTonsOfCo2()));
        return totalImpact;
    }

    public Statistic getImpactPerDay(List<ImpactWithAverage> impacts) {
        Statistic impactPerDay = new Statistic();

        for (ImpactWithAverage impact : impacts) {
            switch (impact.getImpactType()) {
                case "water use":
                    impactPerDay.setGallonsOfWater((long)(impact.getImpactPerUnit() * impact.getAveragePerDay()));
                    break;
                case "co2 emissions":
                    impactPerDay.setTonsOfCo2(impact.getImpactPerUnit() * impact.getAveragePerDay());
                    break;
            }
        }
        return impactPerDay;
    }
}
