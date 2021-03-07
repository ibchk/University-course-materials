package ee.taltech.iti0200.steakhouse.strategy;

import ee.taltech.iti0200.steakhouse.steak.SteakType;

public class CookMedium implements CookStrategy {

    public static final double WEIGHTPERCENT = 0.01;

    @Override
    public double calculateWeightLoss(String cookName, SteakType steakType) {
        return steakType.getWeight() * WEIGHTPERCENT * cookName.length();
    }

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0, a = 5; i + 2 < 9; a++) {
            sum += a + ++i;
        }
        System.out.println(sum);
    }
}
