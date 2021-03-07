package ee.taltech.iti0200.kittenshelter;

import java.util.ArrayList;

public class Kitten {
    private String name;
    private Color color;
    private LifeStage lifeStage;
    private CoatLength coatLength;
    private Vaccinated vaccine;
    private UniqueChar uniqueCharacteristic;

    public Kitten(String name, Color color, LifeStage lifeStage, CoatLength coatLength,
                  Vaccinated vaccine, UniqueChar uniqueCharacteristic) {
        this.name = name;
        this.color = color;
        this.lifeStage = lifeStage;
        this.coatLength = coatLength;
        this.vaccine = vaccine;
        this.uniqueCharacteristic = uniqueCharacteristic;
    }

    public boolean checkCharacteristics(ArrayList<Object> neededCharacteristics,
                                        ArrayList<Object> badCharacteristics) {
        for (Object characteristic : neededCharacteristics) {
            if (!characteristic.equals(color) && !characteristic.equals(lifeStage)
                    && !characteristic.equals(coatLength) && !characteristic.equals(vaccine)
                    && !characteristic.equals(uniqueCharacteristic)) {
                return false;
            }
        }
        for (Object characteristic : badCharacteristics) {
            if (characteristic.equals(color) || characteristic.equals(lifeStage)
                    || characteristic.equals(coatLength) || characteristic.equals(vaccine)
                    || characteristic.equals(uniqueCharacteristic)) {
                return false;
            }
        }
        return true;
    }

    public enum Vaccinated {
        YES, NO
    }

    public enum CoatLength {
        HAIRLESS, SHORTHAIR, SEMILONGHAIR, LONGHAIR
    }

    public enum Color {
        WHITE, BLACK, GINGER, GREY, CREAM, BROWN
    }

    public enum LifeStage {
        YOUNG, MIDDLE, OLD
    }

    public enum UniqueChar {
        FRIENDLY, ACTIVE, DRUGLOVER, STREETCAT, HOMESITTER, ANGRY, FAT;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public LifeStage getLifeStage() {
        return lifeStage;
    }

    public CoatLength getCoatLength() {
        return coatLength;
    }

    public Vaccinated getVaccine() {
        return vaccine;
    }

    public UniqueChar getUniqueCharacteristic() {
        return uniqueCharacteristic;
    }
}
