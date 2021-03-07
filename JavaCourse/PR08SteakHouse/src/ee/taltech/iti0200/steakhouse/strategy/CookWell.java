package ee.taltech.iti0200.steakhouse.strategy;

import ee.taltech.iti0200.steakhouse.steak.SteakType;

public class CookWell implements CookStrategy {

    public static final double WEIGHTPERCENT = 0.2;

    @Override
    public double calculateWeightLoss(String cookName, SteakType steakType) {
        return steakType.getWeight() * WEIGHTPERCENT;
    }
}
