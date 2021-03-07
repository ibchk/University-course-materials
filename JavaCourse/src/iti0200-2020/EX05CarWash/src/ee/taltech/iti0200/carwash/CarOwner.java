public class CarOwner {
    private final String name;
    private int balance;

    public CarOwner(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }
}
