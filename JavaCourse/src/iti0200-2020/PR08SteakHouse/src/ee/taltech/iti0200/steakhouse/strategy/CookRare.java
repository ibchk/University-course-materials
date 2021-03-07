package ee.taltech.iti0200.steakhouse.strategy;

import ee.taltech.iti0200.steakhouse.steak.SteakType;

public class CookRare implements CookStrategy {
    @Override
    public double calculateWeightLoss(String cookName, SteakType steakType) {
        if (cookName.length() % 2 != 0) {
            return cookName.length();
        }
        return 0;
    }
}
