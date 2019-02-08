package com.codingnomads.impacttracker.logic.statistic;

import java.time.LocalDate;

public class ChartRate {
    private double impactPerDay;
    private LocalDate date;

    public ChartRate(double impactPerDay, LocalDate date) {
        this.impactPerDay = impactPerDay;
        this.date = date;
    }

    public double getImpactPerDay() {
        return impactPerDay;
    }

    public void setImpactPerDay(double impactPerDay) {
        this.impactPerDay = impactPerDay;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
