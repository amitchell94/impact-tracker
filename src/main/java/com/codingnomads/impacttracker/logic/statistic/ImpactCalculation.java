package com.codingnomads.impacttracker.logic.statistic;

import com.codingnomads.impacttracker.presentation.ChartPoint;

import java.util.ArrayList;
import java.util.List;

public class ImpactCalculation {
    private double totalImpact;
    private List<ChartRate> chartPoints =  new ArrayList<>();

    public double getTotalImpact() {
        return totalImpact;
    }

    public void setTotalImpact(double totalImpact) {
        this.totalImpact = totalImpact;
    }

    public List<ChartRate> getChartPoints() {
        return chartPoints;
    }

    public void setChartPoints(List<ChartRate> chartPoints) {
        this.chartPoints = chartPoints;
    }
}
