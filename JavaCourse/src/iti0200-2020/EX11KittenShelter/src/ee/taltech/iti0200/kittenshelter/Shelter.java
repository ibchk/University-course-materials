package ee.taltech.iti0200.kittenshelter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Shelter {

    public static final int PRICE35 = 35;

    private String name;
    private String place;
    private Integer kittenMaxCount;
    private Map<Kitten, Integer> kittens = new HashMap<>();
    private Integer shelterLivingDays = 0;
    private Integer maxDayOperations = 3;
    private Integer moneyAmount;

    public Shelter(String name, String place, Integer kittenMaxCount, Integer moneyAmount) {
        this.name = name;
        this.place = place;
        this.kittenMaxCount = kittenMaxCount;
        this.moneyAmount = moneyAmount;
    }

    public boolean adopt(Human human, Kitten kitten) {
        if (maxDayOperations == 0) {
            nextDay();
        }
        if (kittens.containsKey(kitten) && human.getKittens().size() < human.getKittensMaxCount()
                && human.getMoney() >= PRICE35 && kittens.get(kitten) > 3) {
            human.addKitten(kitten);
            human.buy(PRICE35);
            moneyAmount += PRICE35;
            kittens.remove(kitten);
            return true;
        }
        return false;
    }

    public boolean addKitten(Kitten kitten) {
        if (maxDayOperations == 0) {
            nextDay();
        }
        if (!kittens.containsKey(kitten) && kittens.size() < kittenMaxCount) {
            kittens.put(kitten, 0);
            maxDayOperations--;
            return true;
        }
        return false;
    }

    public List<Kitten> kittensToAdopt(ArrayList<Object> neededCharacteristics, ArrayList<Object> badCharacteristics) {
        List<Kitten> kittensForHuman = new ArrayList<>();
        for (Kitten kitten : kittens.keySet()) {
            if (kittens.get(kitten) > 3 && kitten.checkCharacteristics(neededCharacteristics, badCharacteristics)) {
                kittensForHuman.add(kitten);
            }
        }
        return kittensForHuman;
    }

    public void nextDay() {
        moneyAmount -= 5 * kittens.size();
        maxDayOperations = 3;
        shelterLivingDays++;
        for (Map.Entry<Kitten, Integer> kitten : kittens.entrySet()) {
            kitten.setValue(kitten.getValue() + 1);
        }
    }

    public String getName() {
        return name;
    }

    public Map<Kitten, Integer> getKittens() {
        return kittens;
    }

    public String getPlace() {
        return place;
    }

    public Integer getShelterLivingDays() {
        return shelterLivingDays;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Integer moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void setKittenMaxCount(Integer kittenMaxCount) {
        this.kittenMaxCount = kittenMaxCount;
    }

    public Integer getKittenMaxCount() {
        return kittenMaxCount;
    }
}
