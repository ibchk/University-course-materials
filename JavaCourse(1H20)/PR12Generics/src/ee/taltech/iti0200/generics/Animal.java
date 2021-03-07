package ee.taltech.iti0200.generics;

public abstract class Animal {

    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract void sound();
}
