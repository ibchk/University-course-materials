package ee.taltech.iti0200.kittenshelter;

import java.util.LinkedList;
import java.util.List;

public class Human {
    private String name;
    private Integer kittensMaxCount;
    private List<Kitten> kittens = new LinkedList<>();
    private Integer money;

    public Human(String name, Integer kittensMaxCount, Integer money) {
        this.name = name;
        this.kittensMaxCount = kittensMaxCount;
        this.money = money;
    }

    public void addKitten(Kitten kitten) {
        kittens.add(kitten);
    }

    public String getName() {
        return name;
    }

    public List<Kitten> getKittens() {
        return kittens;
    }

    public Integer getKittensMaxCount() {
        return kittensMaxCount;
    }

    public Integer getMoney() {
        return money;
    }

    public void buy(Integer money) {
        this.money -= money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
