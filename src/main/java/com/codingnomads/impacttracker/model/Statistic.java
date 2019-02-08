package com.codingnomads.impacttracker.model;

import com.codingnomads.impacttracker.logic.statistic.ImpactCalculation;

public class Statistic {
    private ImpactCalculation tonsOfCo2 = new ImpactCalculation();
    private ImpactCalculation gallonsOfWater = new ImpactCalculation();

    public ImpactCalculation getTonsOfCo2() {
        return tonsOfCo2;
    }

    public void setTonsOfCo2(ImpactCalculation tonsOfCo2) {
        this.tonsOfCo2 = tonsOfCo2;
    }

    public ImpactCalculation getGallonsOfWater() {
        return gallonsOfWater;
    }

    public void setGallonsOfWater(ImpactCalculation gallonsOfWater) {
        this.gallonsOfWater = gallonsOfWater;
    }
}
