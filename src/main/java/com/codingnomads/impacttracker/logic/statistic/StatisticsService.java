package com.codingnomads.impacttracker.logic.statistic;

import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import com.codingnomads.impacttracker.logic.impact.ImpactService;
import com.codingnomads.impacttracker.model.Commitment;
import com.codingnomads.impacttracker.model.ImpactWithAverage;
import com.codingnomads.impacttracker.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    //This method should be private and nonstatic
    public static Double roundToTwoDP(Double input) {
        if (input == null) return null;
        return (double) Math.round(input * 100d) / 100d;
    }

    public Statistic getImpactForTimePeriod(int userId, int timePeriodInDays) {

        List<Commitment> commitments = commitmentService.getCommitmentsFromUserId(userId);
        Statistic impactInTimePeriod = new Statistic();

        Map<Integer, List<ImpactWithAverage>> reductionImpactMap = new HashMap<>();

        for (Commitment commitment : commitments) {
            long daysCommitted = setDaysCommitted(commitment, timePeriodInDays);

            int reductionId = commitment.getReductionId();

            if (!reductionImpactMap.containsKey(reductionId)) {
                reductionImpactMap.put(reductionId,
                        impactService.getImpactsWithAveragesFromReductionId(reductionId));
            }
            Statistic impactPerDay = getImpactPerDay(reductionImpactMap.get(reductionId),
                    commitment.getAmountToReduceBy());

            impactInTimePeriod.setTonsOfCo2(impactInTimePeriod.getTonsOfCo2() + (impactPerDay.getTonsOfCo2() * daysCommitted));

            impactInTimePeriod.setGallonsOfWater(impactInTimePeriod.getGallonsOfWater()
                    + (impactPerDay.getGallonsOfWater() * daysCommitted));
        }

        impactInTimePeriod.setTonsOfCo2(roundToTwoDP(impactInTimePeriod.getTonsOfCo2()));
        return impactInTimePeriod;
    }

    private Statistic getImpactPerDay(List<ImpactWithAverage> impacts, Integer amountCommitted) {
        Statistic impactPerDay = new Statistic();

        for (ImpactWithAverage impact : impacts) {
            switch (impact.getImpact().getImpactType()) {
                case "water use":
                    if (amountCommitted ==  null || amountCommitted == 0) {
                        impactPerDay.setGallonsOfWater((long) (impact.getImpact().getImpactPerUnit() * impact.getAveragePerDay()));
                    } else {
                        impactPerDay.setGallonsOfWater((long) (impact.getImpact().getImpactPerUnit() * amountCommitted));
                    }
                    break;
                case "co2 emissions":
                    if (amountCommitted ==  null || amountCommitted == 0) {
                        impactPerDay.setTonsOfCo2(impact.getImpact().getImpactPerUnit() * impact.getAveragePerDay());
                    } else {
                        impactPerDay.setTonsOfCo2(impact.getImpact().getImpactPerUnit() * amountCommitted);
                    }
                    break;
            }
        }
        return impactPerDay;
    }

    //the name of this method should be getDaysCommitted, not set
    //Instead of having a long method of 30 lines, it is easier to split it into small methods
    // Did a small refactor but the code still quite complicated!
    private long setDaysCommitted(Commitment commitment, int timePeriodInDays) {
        LocalDate today = LocalDate.now();
        if (commitment.getStartDate().isAfter(today)) {
            return 0;
        }

        LocalDate impactPeriodStartDate = getImpactPeriodStartDate(timePeriodInDays, today);
        LocalDate startCounter = getStartCounter(commitment, impactPeriodStartDate);

        if (commitment.getEndDate() == null || commitment.getEndDate().isAfter(today)) {
            return DAYS.between(startCounter, today);
        } else if (!commitment.getEndDate().isBefore(impactPeriodStartDate)) {
            return DAYS.between(startCounter, commitment.getEndDate());
        }

        return 0;
    }

    private LocalDate getStartCounter(Commitment commitment, LocalDate impactPeriodStartDate) {
        LocalDate startCounter;
        if (commitment.getStartDate().isBefore(impactPeriodStartDate)) {
            startCounter = impactPeriodStartDate;
        } else {
            startCounter = commitment.getStartDate();
        }
        return startCounter;
    }

    private LocalDate getImpactPeriodStartDate(int timePeriodInDays, LocalDate today) {
        LocalDate impactPeriodStartDate = LocalDate.now();
        if (timePeriodInDays == 0) {
            impactPeriodStartDate = LocalDate.MIN;
        } else if (timePeriodInDays == 7) {
            impactPeriodStartDate = today.minusDays(7);
        } else if (timePeriodInDays == 30) {
            impactPeriodStartDate = today.minusMonths(1);
        } else if (timePeriodInDays == 365) {
            impactPeriodStartDate = today.minusYears(1);
        }
        return impactPeriodStartDate;
    }

}
