package ee.taltech.iti0200.burgerbooth.terminal;

import ee.taltech.iti0200.burgerbooth.Food;

import java.util.HashMap;
import java.util.Map;

public class TerminalDriveIn extends Terminal {

    public static final double TIMEWAITPERCENT = 0.5;

    private Map<Food, Integer> order = new HashMap<>();

    @Override
    public Map<Food, Integer> getOrder() {
        return order;
    }

    @Override
    public void addFood(Food food) {
        if (order.containsKey(food)) {
            order.put(food, order.get(food) + 1);
        } else {
            order.put(food, 1);
        }
    }

    @Override
    public void removeFood(Food food) {
        if (order.containsKey(food)) {
            if (order.get(food) > 1) {
                order.put(food, order.get(food) - 1);
            } else {
                order.remove(food);
            }
        }
    }

    @Override
    public double getTimeForCooking() {
        double time = 0.0;
        for (Food food : order.keySet()) {
            time += food.getMakeingTime() * TIMEWAITPERCENT;
        }
        return time;
    }

    @Override
    public void removeOrder() {
        order = new HashMap<>();
    }
}
