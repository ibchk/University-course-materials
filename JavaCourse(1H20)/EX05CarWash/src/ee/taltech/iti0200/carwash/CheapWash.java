public class CheapWash extends WashStrategy {

    public static final int WASHDURATION = 10;
    public static final int DRYDURATION = 11;
    public static final int WASHPRICE = 10;
    public static final int DRYPRICE = 12;
    public static final int DIRTINESS = 40;
    public static final int SETBALANCE2 = 2;

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
        owner.setBalance(owner.getBalance() - SETBALANCE2);
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
