package com.codingnomads.impacttracker.presentation;

public class ChartPoint {
    String x;
    int y;

    public ChartPoint(int y, String x) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
