package com.codingnomads.impacttracker.logic.statistic;

import com.codingnomads.impacttracker.model.Commitment;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import com.codingnomads.impacttracker.logic.impact.ImpactService;
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

    public static Double roundToTwoDP(Double input) {
        if (input == null) return null;
        return (double) Math.round(input * 100d) / 100d;
    }

    public Statistic getImpactForTimePeriod(int userId, int timePeriodInDays) {

        List<Commitment> commitments = commitmentService.getCommitmentsFromUserId(userId);
        Statistic impactInTimePeriod = new Statistic();

        Map<Integer, List<ImpactWithAverage>> reductionImpactMap = new HashMap<>();

        for (Commitment commitment : commitments) {
            long daysCommitted;
            if (timePeriodInDays == 0) {
                if (commitment.getEndDate() == null) {
                    daysCommitted = DAYS.between(commitment.getStartDate(), LocalDate.now());
                } else {
                    daysCommitted = DAYS.between(commitment.getStartDate(), commitment.getEndDate());
                }
            } else {
                daysCommitted = setDaysCommitted(commitment, timePeriodInDays);
            }

            int reductionId = commitment.getReductionId();

            if (!reductionImpactMap.containsKey(reductionId)) {
                reductionImpactMap.put(reductionId,
                        impactService.getImpactsWithAveragesFromReductionId(reductionId));
            }

            Statistic impactPerDay = getImpactPerDay(reductionImpactMap.get(reductionId));

            impactInTimePeriod.setTonsOfCo2(impactInTimePeriod.getTonsOfCo2() + (impactPerDay.getTonsOfCo2() * daysCommitted));

            impactInTimePeriod.setGallonsOfWater(impactInTimePeriod.getGallonsOfWater()
                    + (impactPerDay.getGallonsOfWater() * daysCommitted));
        }

        impactInTimePeriod.setTonsOfCo2(roundToTwoDP(impactInTimePeriod.getTonsOfCo2()));
        return impactInTimePeriod;
    }

    private Statistic getImpactPerDay(List<ImpactWithAverage> impacts) {
        Statistic impactPerDay = new Statistic();

        for (ImpactWithAverage impact : impacts) {
            switch (impact.getImpact().getImpactType()) {
                case "water use":
                    impactPerDay.setGallonsOfWater((long) (impact.getImpact().getImpactPerUnit() * impact.getAveragePerDay()));
                    break;
                case "co2 emissions":
                    impactPerDay.setTonsOfCo2(impact.getImpact().getImpactPerUnit() * impact.getAveragePerDay());
                    break;
            }
        }
        return impactPerDay;
    }

    private long setDaysCommitted(Commitment commitment, int timePeriodInDays) {

        LocalDate today = LocalDate.now();
        LocalDate impactStartDate = LocalDate.now();
        if (timePeriodInDays == 7) {
            impactStartDate = today.minusDays(7);
        } else if (timePeriodInDays == 30) {
            impactStartDate = today.minusMonths(1);
        } else if (timePeriodInDays == 365) {
            impactStartDate = today.minusYears(1);
        }
        LocalDate startCounter = LocalDate.now();
        LocalDate endCounter = LocalDate.now();
        long daysCommitted = 0;

        //if start date is before our counter starts:
        //set start date to our impactStartDate
        if (commitment.getStartDate().isBefore(impactStartDate)) {
            startCounter = impactStartDate;
        }
        //if start date is after our counter starts:
        //use commitment start date
        else if (commitment.getStartDate().isEqual(impactStartDate) || commitment.getStartDate().isAfter(impactStartDate)) {
            startCounter = commitment.getStartDate();
        }
        //if end date is null:
        //use today as end date
        if (commitment.getEndDate() == null) {
            daysCommitted = DAYS.between(startCounter, today);
        }
        //if end date is inside our counter:
        //use actual commitment end date
        else if (commitment.getEndDate().isEqual(impactStartDate) || commitment.getEndDate().isAfter(impactStartDate)) {
            daysCommitted = DAYS.between(startCounter, commitment.getEndDate());
        }
        //if end date is before our counter:
        //completely ignore this commitment
        else if (commitment.getEndDate().isBefore(impactStartDate)) {
            daysCommitted = 0;
        }
        return daysCommitted;
    }

}
