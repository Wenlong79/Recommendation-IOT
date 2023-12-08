package com.wl.entity;

public class OperatingParams {
    private double rotorSpeed;
    private double slack;
    private double rootThreshold;

    public OperatingParams() {
    }

    public OperatingParams(double rotorSpeed, double slack, double rootThreshold) {
        this.rotorSpeed = rotorSpeed;
        this.slack = slack;
        this.rootThreshold = rootThreshold;
    }

    public double getRotorSpeed() {
        return rotorSpeed;
    }

    public void setRotorSpeed(double rotorSpeed) {
        this.rotorSpeed = rotorSpeed;
    }

    public double getSlack() {
        return slack;
    }

    public void setSlack(double slack) {
        this.slack = slack;
    }

    public double getRootThreshold() {
        return rootThreshold;
    }

    public void setRootThreshold(double rootThreshold) {
        this.rootThreshold = rootThreshold;
    }

    @Override
    public String toString() {
        return "OperatingParams{" +
                "rotorSpeed=" + rotorSpeed +
                ", slack=" + slack +
                ", rootThreshold=" + rootThreshold +
                '}';
    }
}
