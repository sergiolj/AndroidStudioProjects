package com.example.orbitsimulator.geometry;

import com.example.orbitsimulator.util.ColorRGB;
import com.example.orbitsimulator.util.PolarCoord;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

public class Geometry {
    private static Geometry instance = null;

    private Supplier<ElementTypes> elementFactory;

    private static final int NUMBER_OF_ELEMENTS = 20;
    private static final int NUMBER_OF_STRUCTURES = 10;
    private static final double MAX_RADIUS = 400;

    /**
     * Deste modo o arraylist aceita typos de elementos diferentes que implementarem
     * a interface ElementTypes
     */
    private ArrayList<ElementTypes> geometrySet;

    // Deslocamento acumulado dos elementos em suas órbitas
    private double displacementSum;

    private double scaleX;
    private double scaleY;
    private double dAngle = 0;

    private final Random rnd = new Random();

    private Geometry(){
        this.geometrySet = new ArrayList<ElementTypes>();
    }

    public static Geometry getInstance(){
        if(instance==null){
            instance = new Geometry();
        }
        return instance;
    }

    /*
     Para adicionar uma flexibilidade de tipos geométricos foi necessário criar essa função para
     possibilitar a injeção do objeto que será criado.
     */
    public void populateGeometrySet(Supplier<ElementTypes> factory) {
       this.scaleX = 1.0F;
       this.scaleY = 1.0F;
       this.dAngle = 0;

       this.elementFactory = factory;

       double radius;
       double angleTeta;

       this.geometrySet.clear();

        for(int i = 1; i <= NUMBER_OF_STRUCTURES; i++){
            for(int j = 1; j <= NUMBER_OF_ELEMENTS; j++){
                ElementTypes element = factory.get();
                radius = i * MAX_RADIUS / NUMBER_OF_STRUCTURES;
                angleTeta = (j - 1) * 2 * Math.PI / NUMBER_OF_ELEMENTS;

                element.setPosition(new PolarCoord(radius,angleTeta));
                element.setColor(new ColorRGB(rand(), rand(), rand()));

                this.geometrySet.add(element);
            }
        }
    }

    private int rand() {
        return 100 + rnd.nextInt(156);
    }

    public void boost(double dC){
        displacementSum += dC;
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    public ArrayList<ElementTypes> getGeometrySet() {
        return geometrySet;
    }

    public ArrayList<ElementTypes> getGeometrySetIncrement(){
        ArrayList<ElementTypes> geometrySetNewPositions = new ArrayList<>();
        if(this.elementFactory != null){
                for(ElementTypes element : this.geometrySet){
                    ElementTypes newElement = this.elementFactory.get();
                    newElement.setColor(element.getColor());

                    PolarCoord oldPos = element.getPosition();
                    double newAngle = oldPos.getAngle() + displacementSum/ oldPos.getRadius();

                    newElement.setPosition(new PolarCoord(oldPos.getRadius(), newAngle));

                    geometrySetNewPositions.add(newElement);
                }
        } else{
            throw new RuntimeException("elementFactory não foi inicializado.");
        }
        return geometrySetNewPositions;
    }

    public static void setInstance(Geometry instance) {
        Geometry.instance = instance;
    }

    public Supplier<ElementTypes> getElementFactory() {
        return elementFactory;
    }

    public void setElementFactory(Supplier<ElementTypes> elementFactory) {
        this.elementFactory = elementFactory;
    }

    public void setGeometrySet(ArrayList<ElementTypes> geometrySet) {
        this.geometrySet = geometrySet;
    }

    public void setDisplacementSum(double displacementSum) {
        this.displacementSum = displacementSum;
    }

    public double getdAngle() {
        return dAngle;
    }

    public void setdAngle(double dAngle) {
        this.dAngle = dAngle;
    }

    public Random getRnd() {
        return rnd;
    }

    public double getDisplacementSum() {
        return displacementSum;
    }
}
