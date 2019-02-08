package com.codingnomads.impacttracker.logic.statistic;

import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import com.codingnomads.impacttracker.logic.impact.ImpactService;
import com.codingnomads.impacttracker.model.Commitment;
import com.codingnomads.impacttracker.model.ImpactWithAverage;
import com.codingnomads.impacttracker.model.Statistic;
import com.codingnomads.impacttracker.presentation.ChartPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public static ChartPoint[] convertChartRatesToChartPointArray(List<ChartRate> chartRates) {
        List<ChartPoint> chartPoints = new ArrayList<>();

        chartRates.sort(Comparator.comparing(ChartRate::getDate));
        double currentImpactRate = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < chartRates.size(); i++) {
            if (chartPoints.isEmpty()) {
                chartPoints.add(new ChartPoint(0, chartRates.get(i).getDate().format(formatter)));
            } else {
                long noOfDays = DAYS.between(chartRates.get(i - 1).getDate(), chartRates.get(i).getDate());
                if (noOfDays <= 0) {
                    noOfDays = 1;
                }
                chartPoints.add(new ChartPoint(chartPoints.get(i - 1).getY() + (noOfDays * currentImpactRate), chartRates.get(i).getDate().format(formatter)));
            }
            currentImpactRate += chartRates.get(i).getImpactPerDay();
        }
        if (!chartPoints.isEmpty()) {
            long noOfDays = DAYS.between(chartRates.get(chartRates.size() - 1).getDate(), LocalDate.now());
            if (noOfDays <= 0) {
                noOfDays = 1;
            }
            chartPoints.add(new ChartPoint(chartPoints.get(chartPoints.size() - 1).getY() + (noOfDays * currentImpactRate), LocalDate.now().format(formatter)));
        }
        ChartPoint[] charPointArray = new ChartPoint[chartPoints.size()];
        chartPoints.toArray(charPointArray);
        return charPointArray;
    }

    public Statistic getImpactForTimePeriod(int userId, int timePeriodInDays) {

        List<Commitment> commitments = commitmentService.getCommitmentsFromUserId(userId);
//        commitments.sort(Comparator.comparing(Commitment::getStartDate));
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

            impactInTimePeriod.getTonsOfCo2().setTotalImpact(impactInTimePeriod.getTonsOfCo2().getTotalImpact() + (impactPerDay.getTonsOfCo2().getTotalImpact() * daysCommitted));

            if (impactPerDay.getTonsOfCo2().getTotalImpact() > 0) {
                impactInTimePeriod.getTonsOfCo2().getChartPoints().add(new ChartRate(impactPerDay.getTonsOfCo2().getTotalImpact(), commitment.getStartDate()));
                if (commitment.getEndDate() != null && !commitment.getEndDate().isAfter(LocalDate.now())) {
                    impactInTimePeriod.getTonsOfCo2().getChartPoints().add(new ChartRate(0 - impactPerDay.getTonsOfCo2().getTotalImpact(), commitment.getEndDate()));
                }
            }

            impactInTimePeriod.getGallonsOfWater().setTotalImpact(impactInTimePeriod.getGallonsOfWater().getTotalImpact()
                    + (impactPerDay.getGallonsOfWater().getTotalImpact() * daysCommitted));

            if (impactPerDay.getGallonsOfWater().getTotalImpact() > 0) {
                impactInTimePeriod.getGallonsOfWater().getChartPoints().add(new ChartRate(impactPerDay.getGallonsOfWater().getTotalImpact(), commitment.getStartDate()));
                if (commitment.getEndDate() != null && !commitment.getEndDate().isAfter(LocalDate.now())) {
                    impactInTimePeriod.getGallonsOfWater().getChartPoints().add(new ChartRate(0 - impactPerDay.getGallonsOfWater().getTotalImpact(), commitment.getEndDate()));
                }
            }
        }

        impactInTimePeriod.getTonsOfCo2().setTotalImpact(roundToTwoDP(impactInTimePeriod.getTonsOfCo2().getTotalImpact()));

        return impactInTimePeriod;
    }

    private Statistic getImpactPerDay(List<ImpactWithAverage> impacts, Integer amountCommitted) {
        Statistic impactPerDay = new Statistic();

        for (ImpactWithAverage impact : impacts) {
            switch (impact.getImpact().getImpactType()) {
                case "water use":
                    if (amountCommitted == null || amountCommitted == 0) {
                        impactPerDay.getGallonsOfWater().setTotalImpact((long) (impact.getImpact().getImpactPerUnit() * impact.getAveragePerDay()));
                    } else {
                        impactPerDay.getGallonsOfWater().setTotalImpact((long) (impact.getImpact().getImpactPerUnit() * amountCommitted));
                    }
                    break;
                case "co2 emissions":
                    if (amountCommitted == null || amountCommitted == 0) {
                        impactPerDay.getTonsOfCo2().setTotalImpact(impact.getImpact().getImpactPerUnit() * impact.getAveragePerDay());
                    } else {
                        impactPerDay.getTonsOfCo2().setTotalImpact(impact.getImpact().getImpactPerUnit() * amountCommitted);
                    }
                    break;
            }
        }
        return impactPerDay;
    }

    private long setDaysCommitted(Commitment commitment, int timePeriodInDays) {

        LocalDate today = LocalDate.now();
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

        LocalDate startCounter;

        if (commitment.getStartDate().isAfter(today)) {
            return 0;
        }

        if (commitment.getStartDate().isBefore(impactPeriodStartDate)) {
            startCounter = impactPeriodStartDate;
        } else {
            startCounter = commitment.getStartDate();
        }

        if (commitment.getEndDate() == null || commitment.getEndDate().isAfter(today)) {
            return DAYS.between(startCounter, today);
        } else if (!commitment.getEndDate().isBefore(impactPeriodStartDate)) {
            return DAYS.between(startCounter, commitment.getEndDate());
        }

        return 0;
    }
}
