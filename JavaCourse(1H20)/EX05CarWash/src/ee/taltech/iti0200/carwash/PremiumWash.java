public class PremiumWash extends WashStrategy {

    public static final int WASHDURATION = 20;
    public static final int DRYDURATION = 30;
    public static final int WASHPRICE = 60;
    public static final int DRYPRICE = 70;
    public static final int DIRTINESS = 100;
    public static final int SETBALANCE10 = 10;

    @Override
    public void wash(Car car, CarOwner owner) {
        setSessionDuration(WASHDURATION);
        setSessionPrice(WASHPRICE);
        owner.setBalance(owner.getBalance() - WASHPRICE);
        car.setDirtiness(car.getDirtiness() - DIRTINESS);
    }

    @Override
    public void dry(Car car, CarOwner owner) {
        setSessionDuration(DRYDURATION);
        setSessionPrice(DRYPRICE);
        owner.setBalance(owner.getBalance() - SETBALANCE10);
    }

    @Override
    public int getWashAndDryPrice() {
        return DRYPRICE;
    }

    @Override
    public int getWashPrice() {
        return WASHPRICE;
    }
}
