public class RegularWash extends WashStrategy {

    public static final int WASHDURATION = 15;
    public static final int DRYDURATION = 20;
    public static final int WASHPRICE = 30;
    public static final int DRYPRICE = 35;
    public static final int DIRTINESS = 70;
    public static final int SETBALANCE5 = 5;

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
        owner.setBalance(owner.getBalance() - SETBALANCE5);
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
