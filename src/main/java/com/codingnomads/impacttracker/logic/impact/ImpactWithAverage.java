package com.codingnomads.impacttracker.logic.impact;

public class ImpactWithAverage {

    private int id;
    private int reductionId;
    private double impactPerUnit;
    private String impactUnit;
    private String impactType;
    private String reductionUnit;
    private double averagePerDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReductionId() {
        return reductionId;
    }

    public void setReductionId(int reductionId) {
        this.reductionId = reductionId;
    }

    public double getImpactPerUnit() {
        return impactPerUnit;
    }

    public void setImpactPerUnit(double impactPerUnit) {
        this.impactPerUnit = impactPerUnit;
    }

    public String getImpactUnit() {
        return impactUnit;
    }

    public void setImpactUnit(String impactUnit) {
        this.impactUnit = impactUnit;
    }

    public String getImpactType() {
        return impactType;
    }

    public void setImpactType(String impactType) {
        this.impactType = impactType;
    }

    public String getReductionUnit() {
        return reductionUnit;
    }

    public void setReductionUnit(String reductionUnit) {
        this.reductionUnit = reductionUnit;
    }

    public double getAveragePerDay() {
        return averagePerDay;
    }

    public void setAveragePerDay(double averagePerDay) {
        this.averagePerDay = averagePerDay;
    }
}
