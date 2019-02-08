package com.codingnomads.impacttracker.presentation;

public class ChartPoint {
    String x;
    double y;

    public ChartPoint(double y, String x) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
