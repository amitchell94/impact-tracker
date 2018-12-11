package com.codingnomads.impacttracker.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Commitment {

    private Integer id;
    private Integer userId;
    private Integer reductionId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Integer amountToReduceBy;

    public Commitment() {
    }

    public Commitment(Integer id, LocalDate startDate, LocalDate endDate, Integer amountToReduceBy, Integer userId, Integer reductionId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountToReduceBy = amountToReduceBy;
        this.userId = userId;
        this.reductionId = reductionId;
    }


    public Commitment(Integer userId, Integer reductionId, LocalDate startDate, LocalDate endDate, Integer amountToReduceBy) {
        this.userId = userId;
        this.reductionId = reductionId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountToReduceBy = amountToReduceBy;
    }

    @Override
    public String toString() {
        return "Commitment{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amountToReduceBy=" + amountToReduceBy +
                ", userId=" + userId +
                ", reductionId=" + reductionId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReductionId() {
        return reductionId;
    }

    public void setReductionId(Integer reductionId) {
        this.reductionId = reductionId;
    }
}
