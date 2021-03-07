package ee.taltech.iti0200.burgerbooth.terminal;

import ee.taltech.iti0200.burgerbooth.Food;

import java.util.Map;

public abstract class Terminal {
    public abstract Map<Food, Integer> getOrder();

    public abstract void addFood(Food food);

    public abstract void removeFood(Food food);

    public abstract double getTimeForCooking();

    public abstract void removeOrder();
}
