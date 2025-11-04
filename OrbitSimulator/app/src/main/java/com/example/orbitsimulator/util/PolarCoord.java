package com.example.orbitsimulator.util;

public class PolarCoord {
    private double radius;
    private double angle;

    public PolarCoord(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
    }
    public PolarCoord(){
        this.radius = 0;
        this.angle = 0;
    }

    public double getAngle() {
        return angle;
    }

    public double getRadius() {
        return radius;
    }

}
