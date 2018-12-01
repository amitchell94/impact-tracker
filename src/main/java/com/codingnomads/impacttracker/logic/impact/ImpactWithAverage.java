package com.codingnomads.impacttracker.logic.impact;

public class ImpactWithAverage {

    private Impact impact = new Impact();
    private double averagePerDay;

    public Impact getImpact() {
        return impact;
    }

    public void setImpact(Impact impact) {
        this.impact = impact;
    }

    public double getAveragePerDay() {
        return averagePerDay;
    }

    public void setAveragePerDay(double averagePerDay) {
        this.averagePerDay = averagePerDay;
    }
}
