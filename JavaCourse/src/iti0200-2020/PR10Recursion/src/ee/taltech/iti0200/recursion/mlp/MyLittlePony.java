package ee.taltech.iti0200.recursion.mlp;

public class MyLittlePony {
    public enum PonyType {
        EARTH_PONY, UNICORN, ALICORN, PEGASUS;
    }

    String name;
    PonyType type;

    public MyLittlePony(String name, PonyType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PonyType getPonyType() {
        return type;
    }
}
