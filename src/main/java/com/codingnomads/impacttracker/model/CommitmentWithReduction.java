package com.codingnomads.impacttracker.model;

import java.time.LocalDate;

public class CommitmentWithReduction {
    private Integer id;
    private Integer userId;
    private Integer reductionId;
    private String reduction;
    private String reductionUnit;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer amountToReduceBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReductionId() {
        return reductionId;
    }

    public String getReductionUnit() {
        return reductionUnit;
    }

    public void setReductionUnit(String reductionUnit) {
        this.reductionUnit = reductionUnit;
    }

    public void setReductionId(Integer reductionId) {
        this.reductionId = reductionId;
    }

    public String getReduction() {
        return reduction;
    }

    public void setReduction(String reduction) {
        this.reduction = reduction;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getAmountToReduceBy() {
        return amountToReduceBy;
    }

    public void setAmountToReduceBy(Integer amountToReduceBy) {
        this.amountToReduceBy = amountToReduceBy;
    }
}
