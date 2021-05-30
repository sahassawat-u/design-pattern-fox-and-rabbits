package io.muic.ooc.fab;

import java.awt.*;

public enum HumanType {
    HUNTER(0.005,Hunter.class, Color.BLACK);

    private Class humanClass;
    private double breedingProbability;
    private Color color;

    public Class getHumanClass() {
        return humanClass;
    }

    public Color getColor() {
        return color;
    }

    HumanType(double breedingProbability,Class humanClass, Color color) {
        this.breedingProbability = breedingProbability;
        this.humanClass = humanClass;
        this.color = color;
    }
    public double getBreedingProbability() {
        return breedingProbability;
    }

}
