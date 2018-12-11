package com.codingnomads.impacttracker.model;

public class Reduction {

    private Integer id;
    private String reduction;
    private Double averagePerDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReduction() {
        return reduction;
    }

    public void setReduction(String reduction) {
        this.reduction = reduction;
    }

    public Double getAveragePerDay() {
        return averagePerDay;
    }

    public void setAveragePerDay(Double averagePerDay) {
        this.averagePerDay = averagePerDay;
    }
}
