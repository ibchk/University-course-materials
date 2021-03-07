package ee.taltech.iti0200.steakhouse;

import ee.taltech.iti0200.steakhouse.steak.Steak;
import ee.taltech.iti0200.steakhouse.steak.SteakType;

import java.util.ArrayDeque;

public class Kitchen {
    private String chefCook;

    ArrayDeque<String> allCooks = new ArrayDeque<>();

    public Kitchen(String chefCook) {
        this.chefCook = chefCook;
    }

    public String getChefCook() {
        return chefCook;
    }

    public void setChefCook(String chefCook) {
        this.chefCook = chefCook;
    }

    public Steak makeOrder(Order order) {
        if (order.getSteakType().equals(SteakType.FILET_MIGNON)) {
            return order.getCookStrategy().makeSteak(chefCook, order.getSteakType());
        }
        String neededCook = allCooks.getFirst();
        allCooks.removeFirst();
        allCooks.addLast(neededCook);
        return order.getCookStrategy().makeSteak(neededCook, order.getSteakType());
    }

    public void addCook(String cookName) {
        allCooks.add(cookName);
    }

    public ArrayDeque<String> getAllCooks() {
        return allCooks;
    }
}
