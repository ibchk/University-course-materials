public class Car {

    public static final int DIRTINESS0 = 0;

    private int dirtiness;

    public Car(int dirtiness) {
        this.dirtiness = dirtiness;
    }

    public void setDirtiness(int dirtiness) {
        this.dirtiness = Math.max(dirtiness, DIRTINESS0);
    }

    public int getDirtiness() {
        return dirtiness;
    }
}
