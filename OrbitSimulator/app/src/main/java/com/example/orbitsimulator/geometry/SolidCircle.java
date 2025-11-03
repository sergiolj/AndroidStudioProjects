package com.example.orbitsimulator.geometry;

import com.example.orbitsimulator.util.ColorRGB;
import com.example.orbitsimulator.util.PolarCoord;

public class SolidCircle implements ElementTypes {
    private int size;
    private ColorRGB color;
    private PolarCoord position;

    public SolidCircle(){
        this.color = new ColorRGB();
        this.position = new PolarCoord();
        this.size = 3; // default
    }

    public SolidCircle(SolidCircle c){
        this.color = new ColorRGB(c.color.getR(),c.color.getB(),c.color.getB());
        this.position = new PolarCoord(c.position.getRadius(), c.position.getAngle());
        this.size = c.size; // default
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ColorRGB getColor() {
        return color;
    }

    public void setColor(ColorRGB color) {
        this.color = color;
    }

    public PolarCoord getPosition() {
        return position;
    }

    public void setPosition(PolarCoord position) {
        this.position = position;
    }
}
